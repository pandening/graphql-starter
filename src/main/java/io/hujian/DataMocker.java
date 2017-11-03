package io.hujian;

import io.hujian.model.AuthorModel;
import io.hujian.model.CommentModel;
import io.hujian.model.ContentModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hujian06 on 2017/11/2.
 *
 * mock the data
 */
public class DataMocker {

    public static class DataHolder {
        public static final List<AuthorModel> authorModelList = mockAuthor(); // the author list
        public static final List<ContentModel> contentModelList = mockContent(); // the content list
        public static final List<CommentModel> commentModelList = mockComment(); // the comment list
    }

    private static List<AuthorModel> mockAuthor() {
        AuthorModel authorModel = null;
        List<AuthorModel> authorModelList = new ArrayList<>();
        List<Integer> friends = null;

        //the first author
        authorModel = new AuthorModel();
        authorModel.setAuthorId(1);
        authorModel.setAuthorAge(24);
        authorModel.setAuthorLevel(10);
        authorModel.setAuthorAddr("Fib-301");
        friends = new ArrayList<>();
        friends.add(2);
        friends.add(3);
        authorModel.setFriends(friends);
        authorModelList.add(authorModel);

        //the second author
        authorModel = new AuthorModel();
        authorModel.setAuthorId(2);
        authorModel.setAuthorAge(32);
        authorModel.setAuthorLevel(4);
        authorModel.setAuthorAddr("Ty-0021");
        friends = new ArrayList<>();
        friends.add(1);
        authorModel.setFriends(friends);
        authorModelList.add(authorModel);

        //the 3-rd author
        authorModel = new AuthorModel();
        authorModel.setAuthorId(3);
        authorModel.setAuthorAge(14);
        authorModel.setAuthorLevel(2);
        authorModel.setAuthorAddr("Kky-901");
        friends = new ArrayList<>();
        friends.add(2);
        authorModel.setFriends(friends);
        authorModelList.add(authorModel);

        return authorModelList;
    }

    private static List<ContentModel> mockContent() {
        List<ContentModel> contentModelList = new ArrayList<>();
        ContentModel contentModel = null;
        List<Integer> comments = null;

        //the first content
        contentModel = new ContentModel();
        contentModel.setAuthorId(1);
        contentModel.setContentId(1);
        contentModel.setCommentSize(1);
        contentModel.setText("This is a test content!");
        comments = new ArrayList<>();
        comments.add(2);
        contentModel.setCommentIds(comments);
        contentModelList.add(contentModel);

        //the second content
        contentModel = new ContentModel();
        contentModel.setAuthorId(2);
        contentModel.setContentId(2);
        contentModel.setCommentSize(2);
        contentModel.setText("Graphql is cool for api developer!");
        comments = new ArrayList<>();
        comments.add(1);
        comments.add(3);
        contentModel.setCommentIds(comments);
        contentModelList.add(contentModel);

        return contentModelList;
    }

    private static List<CommentModel> mockComment() {
        List<CommentModel> commentModelList = new ArrayList<>();
        CommentModel commentModel = null;

        //the first comment
        commentModel = new CommentModel();
        commentModel.setAuthorId(2);
        commentModel.setCommentId(1);
        commentModel.setOfContentId(1);
        commentModel.setContent("is test ok?");
        commentModelList.add(commentModel);

        //the second comment
        commentModel = new CommentModel();
        commentModel.setAuthorId(1);
        commentModel.setCommentId(2);
        commentModel.setOfContentId(2);
        commentModel.setContent("i thing so.");
        commentModelList.add(commentModel);

        //the third comment
        commentModel = new CommentModel();
        commentModel.setAuthorId(3);
        commentModel.setCommentId(3);
        commentModel.setOfContentId(2);
        commentModel.setContent("haha, this's true !");
        commentModelList.add(commentModel);

        return commentModelList;
    }


}
