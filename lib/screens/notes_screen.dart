import 'dart:ui';
import 'package:flutter/material.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:maxpense/models/note.dart';
import 'package:maxpense/services/notes_db.dart';

class NotesScreen extends StatefulWidget {
  const NotesScreen({super.key});

  @override
  State<NotesScreen> createState() => _NotesScreenState();
}

class _NotesScreenState extends State<NotesScreen> {
  final searchCtrl = TextEditingController();
  List<Note> notes = [];
  late String userId;

  @override
  void initState() {
    super.initState();
    final user = FirebaseAuth.instance.currentUser;
    userId = user?.uid ?? "guest";
    _loadNotes();
  }

  @override
  void dispose() {
    searchCtrl.dispose();
    super.dispose();
  }

  Future<void> _loadNotes() async {
    notes = await NotesDatabase.instance.readAllNotes(userId);
    setState(() {});
  }

  Future<void> _searchNote(String keyword) async {
    notes = keyword.isEmpty
        ? await NotesDatabase.instance.readAllNotes(userId)
        : await NotesDatabase.instance.searchNotes(keyword, userId);
    setState(() {});
  }

  void _showNoteDialog({Note? note}) {
    final titleCtrl = TextEditingController(text: note?.title ?? '');
    final contentCtrl = TextEditingController(text: note?.content ?? '');

    showDialog(
      context: context,
      barrierDismissible: true,
      builder: (context) {
        return Dialog(
          backgroundColor: Colors.white,
          insetPadding: const EdgeInsets.all(20),
          shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(20)),
          child: Padding(
            padding: const EdgeInsets.all(20),
            child: Column(
              mainAxisSize: MainAxisSize.min,
              crossAxisAlignment: CrossAxisAlignment.stretch,
              children: [
                Text(
                  note == null ? "Add Note" : "Edit Note",
                  style: const TextStyle(
                    fontSize: 20,
                    fontWeight: FontWeight.bold,
                    color: Color(0xFF575DFB),
                  ),
                  textAlign: TextAlign.center,
                ),
                const SizedBox(height: 16),

                // Title
                TextField(
                  controller: titleCtrl,
                  style: const TextStyle(color: Colors.black87),
                  decoration: const InputDecoration(
                    labelText: "Title",
                    labelStyle: TextStyle(color: Color(0xFF575DFB)),
                    focusedBorder: UnderlineInputBorder(
                      borderSide: BorderSide(color: Color(0xFF575DFB)),
                    ),
                  ),
                ),
                const SizedBox(height: 12),

                // Content
                TextField(
                  controller: contentCtrl,
                  style: const TextStyle(color: Colors.black87),
                  decoration: const InputDecoration(
                    labelText: "Content",
                    labelStyle: TextStyle(color: Color(0xFF575DFB)),
                    focusedBorder: UnderlineInputBorder(
                      borderSide: BorderSide(color: Color(0xFF575DFB)),
                    ),
                  ),
                  maxLines: 3,
                ),
                const SizedBox(height: 20),

                // Action Button
                Row(
                  mainAxisAlignment: MainAxisAlignment.end,
                  children: [
                    TextButton(
                      onPressed: () => Navigator.pop(context),
                      child: const Text(
                        "Cancel",
                        style: TextStyle(color: Colors.black54),
                      ),
                    ),
                    const SizedBox(width: 10),
                    ElevatedButton(
                      style: ElevatedButton.styleFrom(
                        backgroundColor: const Color(0xFF575DFB),
                        shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(12),
                        ),
                      ),
                      onPressed: () async {
                        final title = titleCtrl.text.trim();
                        final content = contentCtrl.text.trim();
                        if (title.isEmpty && content.isEmpty) return;

                        if (note == null) {
                          // CREATE
                          await NotesDatabase.instance.create(
                            Note(
                              title: title,
                              content: content,
                              userId: userId,
                            ),
                          );
                        } else {
                          // UPDATE
                          await NotesDatabase.instance.update(
                            Note(
                              id: note.id,
                              title: title,
                              content: content,
                              userId: userId,
                            ),
                          );
                        }
                        if (context.mounted) Navigator.pop(context);
                        await _loadNotes();
                      },
                      child: const Text("Save",
                          style: TextStyle(color: Colors.white)),
                    ),
                  ],
                ),
              ],
            ),
          ),
        );
      },
    );
  }

  Future<void> _deleteNote(int id) async {
    await NotesDatabase.instance.delete(id, userId);
    await _loadNotes();
  }

  Widget _glossyCard({required Widget child}) {
    return Container(
      margin: const EdgeInsets.only(bottom: 12),
      decoration: BoxDecoration(
        boxShadow: [
          BoxShadow(
            color: Colors.black.withOpacity(0.12),
            blurRadius: 10,
            offset: const Offset(0, 6),
          ),
        ],
        borderRadius: BorderRadius.circular(16),
      ),
      child: ClipRRect(
        borderRadius: BorderRadius.circular(16),
        child: BackdropFilter(
          filter: ImageFilter.blur(sigmaX: 8, sigmaY: 8),
          child: Container(
            padding: const EdgeInsets.all(16),
            decoration: BoxDecoration(
              gradient: LinearGradient(
                colors: [
                  const Color(0xFF575DFB).withOpacity(0.6),
                  const Color(0xFF575DFB).withOpacity(0.3),
                ],
                begin: Alignment.topLeft,
                end: Alignment.bottomRight,
              ),
              borderRadius: BorderRadius.circular(16),
              border: Border.all(color: Colors.white.withOpacity(0.2)),
            ),
            child: child,
          ),
        ),
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: const Color(0xFFF5F6FA),
      appBar: AppBar(
        backgroundColor: Colors.transparent,
        elevation: 0,
        title: const Text(
          'My Notes',
          style: TextStyle(fontWeight: FontWeight.bold, color: Colors.black87),
        ),
        centerTitle: true,
        iconTheme: const IconThemeData(color: Colors.black87),
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          children: [
            /// Search Bar
            _glossyCard(
              child: TextField(
                controller: searchCtrl,
                decoration: const InputDecoration(
                  hintText: 'Search Notes...',
                  prefixIcon: Icon(Icons.search, color: Colors.white),
                  border: InputBorder.none,
                  hintStyle: TextStyle(color: Colors.white70),
                ),
                style: const TextStyle(color: Colors.white),
                onChanged: _searchNote,
              ),
            ),
            const SizedBox(height: 16),

            /// Notes List
            Expanded(
              child: notes.isEmpty
                  ? const Center(
                      child: Text(
                        "No notes yet. Tap + to add one!",
                        style: TextStyle(color: Colors.black54),
                      ),
                    )
                  : ListView.builder(
                      itemCount: notes.length,
                      itemBuilder: (context, index) {
                        final note = notes[index];
                        return _glossyCard(
                          child: ListTile(
                            title: Text(
                              note.title,
                              style: const TextStyle(
                                fontSize: 18,
                                fontWeight: FontWeight.bold,
                                color: Colors.white,
                              ),
                            ),
                            subtitle: Text(
                              note.content,
                              style: const TextStyle(
                                  fontSize: 14, color: Colors.white70),
                              maxLines: 2,
                              overflow: TextOverflow.ellipsis,
                            ),
                            trailing: IconButton(
                              icon: const Icon(Icons.delete, color: Colors.white),
                              onPressed: () => _deleteNote(note.id!),
                            ),
                            onTap: () => _showNoteDialog(note: note),
                          ),
                        );
                      },
                    ),
            ),
          ],
        ),
      ),
      floatingActionButton: FloatingActionButton.extended(
        onPressed: () => _showNoteDialog(),
        backgroundColor: Colors.white,
        elevation: 6,
        shape: RoundedRectangleBorder(
          borderRadius: BorderRadius.circular(14),
        ),
        icon: const Icon(
          Icons.add_comment_rounded,
          color: Color(0xFF575DFB),
        ),
        label: const Text(
          "New Note",
          style: TextStyle(
            color: Color(0xFF575DFB),
            fontWeight: FontWeight.w600,
          ),
        ),
      ),
      floatingActionButtonLocation: FloatingActionButtonLocation.endFloat,
    );
  }
}
