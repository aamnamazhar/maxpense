class Note{
  String content;
  int? id;
  String title;
  String userId; 

  Note({
    this.id,
    required this.userId,
    required this.title,
    required this.content,
  });

  Map<String, dynamic> toMap() {
    return {
      'id': id,
      'userId': userId,
      'title': title,
      'content': content,
    };
  }

  factory Note.fromMap(Map<String, dynamic> map) {
    return Note(
      title: map['title'],
      userId: map['userId'],
      content: map['content'],
      id: map['id'],
    );
  }
}