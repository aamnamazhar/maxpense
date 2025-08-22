class Note {
  String content;
  int? id;
  String title;
  String? userId;
  String? firestoreId;

  Note({
    this.id,
    required this.userId,
    required this.title,
    required this.content,
    this.firestoreId,
  });

  Map<String, dynamic> toMap() {
    return {
      'id': id,
      'firestore_id': firestoreId,
      'userId': userId,
      'title': title,
      'content': content,
    };
  }

  static Note fromMap(Map<String, dynamic> map) {
    return Note(
      id: map['id'],
      firestoreId: map['firestore_id'],
      userId: map['userId'],
      title: map['title'],
      content: map['content'],
    );
  }
}
