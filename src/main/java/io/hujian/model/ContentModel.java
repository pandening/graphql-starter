package io.hujian.model;

import java.util.List;

/**
 * Created by hujian06 on 2017/11/2.
 *
 * the model of the content
 */
public class ContentModel {

    private int contentId; // the content id
    private int authorId; // the author id
    private int commentSize; // the comment size of this content

    private String text; // the text
    private List<Integer> commentIds; // the Comment id list

    /**
     * just test the mocker
     * @return s
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ContentId:" + contentId + ",AuthorId:" + authorId + "," +
                "CommentSize:" + commentSize + ",text:" + text + ", comments:");

        for (int i = 0; i < commentIds.size(); i ++) {
            sb.append(commentIds.get(i) + " ");
        }

        return sb.toString();
    }

    public int getContentId() {
        return contentId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getCommentSize() {
        return commentSize;
    }

    public void setCommentSize(int commentSize) {
        this.commentSize = commentSize;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Integer> getCommentIds() {
        return commentIds;
    }

    public void setCommentIds(List<Integer> commentIds) {
        this.commentIds = commentIds;
    }
}
