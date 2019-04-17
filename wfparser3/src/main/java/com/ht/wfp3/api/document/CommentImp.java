package com.ht.wfp3.api.document;

class CommentImp implements Comment {
  private final String commentString;

  CommentImp(String commentString) {
    this.commentString = commentString;
  }

  @Override
  public String getCommentString() {
    return commentString;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((commentString == null) ? 0 : commentString.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    CommentImp other = (CommentImp) obj;
    if (commentString == null) {
      if (other.commentString != null)
        return false;
    } else if (!commentString.equals(other.commentString))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "CommentImp [commentString=" + commentString + "]";
  }
}
