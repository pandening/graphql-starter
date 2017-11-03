package io.hujian.service;

import io.hujian.DataMocker;
import io.hujian.model.CommentModel;
import io.hujian.model.CompletableContentModel;
import io.hujian.model.ContentModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hujian06 on 2017/11/2.
 *
 * the content service mocker
 */
@Service("contentService")
public class ContentService {

    private static CommentService commentService = new CommentService();

    private static List<ContentModel> contentModelList = DataMocker.DataHolder.contentModelList;

    public ContentModel getContentByContentId(int contentId) {
        ContentModel contentModel = null;

        for (ContentModel model : contentModelList) {
            if (model.getContentId() == contentId) {
                contentModel = model;
                break;
            }
        }

        if (contentModel == null) {
            contentModel = new ContentModel();
            contentModel.setContentId(-1);
        }

        return contentModel;
    }

    public CompletableContentModel getComplableContentByContentId(int contentId) {
        //get the basic information
        ContentModel contentModel = getContentByContentId(contentId);
        CompletableContentModel completableContentModel = new CompletableContentModel();

        completableContentModel.setContentId(contentModel.getContentId());
        completableContentModel.setAuthorId(contentModel.getAuthorId());
        completableContentModel.setCommentIds(contentModel.getCommentIds());
        completableContentModel.setCommentSize(contentModel.getCommentSize());
        completableContentModel.setText(contentModel.getText());

        if (contentModel.getContentId() == -1) { // null
            completableContentModel.setContentId(-1);

            return completableContentModel;
        }

        //get extra information of the content
        List<CommentModel> commentModelList = new ArrayList<>();

        for (int id : contentModel.getCommentIds()) {
            CommentModel commentModel = commentService.getCommentByCommentId(id);
            commentModelList.add(commentModel);
        }

        completableContentModel.setCommentModelList(commentModelList);

        return completableContentModel;
    }

    public List<CompletableContentModel> getCompletableContentsByAuthorId(int authorId) {
        List<CompletableContentModel> completableContentModelList = new ArrayList<>();
        CompletableContentModel completableContentModel = null;
        for (ContentModel model : contentModelList) {
            if (model.getAuthorId() == authorId) {
                completableContentModel = getComplableContentByContentId(model.getContentId());
                completableContentModelList.add(completableContentModel);
            }
        }

        return completableContentModelList;
    }


}
