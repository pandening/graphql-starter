package io.hujian.model;

import java.util.List;

/**
 * Created by hujian06 on 2017/11/3.
 *
 * the completable content inofrmation
 */
public class CompletableContentModel extends ContentModel{

    private List<CommentModel> commentModelList; // the comment info list of this content


    public List<CommentModel> getCommentModelList() {
        return commentModelList;
    }

    public void setCommentModelList(List<CommentModel> commentModelList) {
        this.commentModelList = commentModelList;
    }

}
