package io.hujian.service;

import io.hujian.DataMocker;
import io.hujian.model.CommentModel;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hujian06 on 2017/11/2.
 *
 * the comment service mocker
 */
@Service
public class CommentService {

    private static final List<CommentModel> commentModelList = DataMocker.DataHolder.commentModelList;

    public CommentModel getCommentByCommentId(int commentId) {
        CommentModel commentModel = null;

        for (CommentModel model : commentModelList) {
            if (model.getCommentId() == commentId) {
                commentModel = model;
                break;
            }
        }

        if (commentModel == null) {
            commentModel = new CommentModel();
            commentModel.setCommentId(-1);
        }

        return commentModel;
    }

}
