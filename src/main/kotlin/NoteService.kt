import kotlin.math.truncate

class NoteService(val ownerId: Int,
                  val noteId: Int,
                  val title: String,
                  var text: String,
                  val commentsList: MutableList<NoteComments>){


    fun add(noteList: MutableList<NoteService>, note: NoteService): Boolean {
        return noteList.add(note)
    }

    fun createComment(text: String) : Int {
        var comment = NoteComments(
            this.noteId,
            this.commentsList.lastIndex + 1,
            text,
            false
        )
        this.commentsList.add(comment)
        return commentsList.indexOf(comment) + 1
    }

    fun delete(id: Int, noteList: MutableList<NoteService>): Boolean{
      try {
          noteList.removeAt(id)
      }catch (e: Exception){
          return false
      }
        return true
    }

    fun deleteComment(commentId: Int): Boolean{
        try {
            this.commentsList.removeAt(commentId)
        }catch (e: Exception){
            return false
        }
        this.commentsList.get(commentId).isCommentDeleted = true
        return true
    }

    fun edit(text: String) : Boolean{
        var temp = true
        try {
            this.text = text
        }catch (e: Exception){
            temp = false
        }
        return temp
    }


    fun editComment(commentID: Int, text: String): Boolean {
        return if(!this.commentsList.get(commentID).isCommentDeleted){
            this.commentsList.get(commentID).message = text
            true
        }else return false
    }

    fun get(ownerId: Int, noteList: MutableList<NoteService>): List<NoteService> {
        var noteListUser: MutableList<NoteService> = mutableListOf()
        for (noteLoop in noteList) {
            if (ownerId == noteLoop.ownerId) {
                noteListUser.add(noteListUser.lastIndex + 1, noteLoop)
            }
        }
        return noteListUser
    }


    fun getById(noteId: Int, noteList: MutableList<NoteService>): NoteService? {
        var note: NoteService? = null
        for (noteLoop in noteList) {
            if (noteLoop.noteId == noteId) {
                note = noteLoop
            }
        }
        return note
    }

    fun getComments(): MutableList<NoteComments> {
        return this.commentsList
    }

    fun restoreComment(commentId: Int): Boolean {
        var result = false
        if (this.commentsList.get(commentId).isCommentDeleted) {
            this.commentsList.add(commentId, this.commentsList.get(commentId))
            result = true
        }
        return result
    }
}