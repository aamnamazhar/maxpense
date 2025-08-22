import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:maxpense/models/note.dart';
import 'package:path/path.dart';
import 'package:sqflite/sqflite.dart';
import 'package:firebase_auth/firebase_auth.dart';

class NotesDatabase {
  static final NotesDatabase instance = NotesDatabase._init();
  static Database? _database;
  final _firestore= FirebaseFirestore.instance;
  final _auth = FirebaseAuth.instance;

  NotesDatabase._init();

  Future<Database> get database async {
    if (_database != null) return _database!;
    _database = await _initDB('notes_v2.db');
    return _database!;
  }

 Future<Database> _initDB(String filePath) async {
  final dbPath = await getDatabasesPath();
  final path = join(dbPath, filePath);

  return await openDatabase(
    path,
    version: 2,
    onCreate: _createDB,
    onUpgrade: _upgradeDB,
  );
}

  Future _createDB(Database db, int version) async {
    await db.execute('''
    CREATE TABLE notes(
      id INTEGER PRIMARY KEY AUTOINCREMENT,
      userId TEXT NOT NULL,
      title TEXT NOT NULL,
      content TEXT NOT NULL,
      firestore_id TEXT
    )
    ''');
  }

  Future _upgradeDB(Database db, int oldVersion, int newVersion) async {
    if (oldVersion < 2) {
      await db.execute("ALTER TABLE notes ADD COLUMN firestore_id TEXT");
    }
  }

// CRUD Operations
  Future<int> create(Note note) async {
    final db = await instance.database;
    return await db.insert('notes', note.toMap());
  }

  Future<List<Note>> readAllNotes(String userId) async {
    final db = await instance.database;
    final rows = await db.query(
      'notes',
      where: 'userId = ?',
      whereArgs: [userId],
      orderBy: 'id',
    );
    return rows.map(Note.fromMap).toList();
  }

  Future<int> update(Note note) async {
    final db = await instance.database;
    return await db.update(
      'notes',
      note.toMap(),
      where: 'id = ? AND userId = ?',
      whereArgs: [note.id, note.userId],
    );
  }

  Future<int> delete(int id, String userId) async {
    final db = await instance.database;
    return await db.delete(
      'notes',
      where: 'id = ? AND userId = ?',
      whereArgs: [id, userId],
    );
  }

  Future<List<Note>> searchNotes(String keyword, String userId) async {
    final db = await instance.database;
    final rows = await db.query(
      'notes',
      where: '(title LIKE ? OR content LIKE ?) AND userId = ?',
      whereArgs: ['%$keyword%', '%$keyword%', userId],
      orderBy: 'id',
    );
    return rows.map(Note.fromMap).toList();
  }

//backup and restore
Future<void> backupNotes(List<Note> notes) async {
  try {
    final user = _auth.currentUser;
    if (user == null) throw Exception("User not logged in");

    final notesRef = _firestore
        .collection("users")
        .doc(user.uid)
        .collection("notes");

    for (var note in notes) {
      try {
        final docId = note.firestoreId?.isNotEmpty == true
            ? note.firestoreId!
            : notesRef.doc().id;

        await notesRef.doc(docId).set({
          "title": note.title,
          "content": note.content,
          "createdAt": FieldValue.serverTimestamp(),
        });

        if (note.firestoreId == null || note.firestoreId!.isEmpty) {
          final db = await instance.database;
          await db.update(
            'notes',
            {"firestore_id": docId},
            where: 'id = ?',
            whereArgs: [note.id],
          );
        }
      } catch (e) {
        print("⚠️ Failed to back up note '${note.title}': $e");
      }
    }

    print("✅ Backup complete without duplicates");
  } catch (e) {
    print("❌ Backup failed: $e");
    rethrow;
  }
}


Future<List<Note>> restoreNotes() async {
  final user = _auth.currentUser;
  if (user == null) throw Exception("Not logged in");

  final notesSnap = await _firestore
      .collection("users")
      .doc(user.uid)
      .collection("notes")
      .get();

  List<Note> notes = [];

  for (var doc in notesSnap.docs) {
    final data = doc.data();
    final note = Note(
      id: null,
      firestoreId: doc.id,
      userId: user.uid,
      title: data['title'] ?? '',
      content: data['content'] ?? '',
    );

    final exists = await NotesDatabase.instance.noteExists(doc.id);
    if (!exists) {
      await NotesDatabase.instance.create(note);
    }
      notes.add(note);
    }

    print("✅ Restore complete without duplicates");
    return notes;
}
Future<bool> noteExists(String firestoreId) async {
  final db = await instance.database;
  final res = await db.query(
    'notes',
    where: 'firestore_id = ?',
    whereArgs: [firestoreId],
  );
  return res.isNotEmpty;
}
}

