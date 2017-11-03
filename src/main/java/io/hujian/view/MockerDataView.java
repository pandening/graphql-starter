package io.hujian.view;

import com.fasterxml.jackson.core.JsonGenerator;
import io.hujian.DataMocker;
import io.hujian.common.JsonFactoryHolder;
import io.hujian.model.AuthorModel;
import io.hujian.model.CommentModel;
import io.hujian.model.ContentModel;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by hujian06 on 2017/11/2.
 *
 * the view of mocker
 */
public class MockerDataView {

    //get the mock data

    private List<AuthorModel> authorModelList = DataMocker.DataHolder.authorModelList;
    private List<ContentModel> contentModelList = DataMocker.DataHolder.contentModelList;
    private List<CommentModel> commentModelList = DataMocker.DataHolder.commentModelList;

    public void writeView(OutputStream outputStream) throws IOException {

        try (JsonGenerator jsonFactory = JsonFactoryHolder.getJsonFactory()
                .createGenerator(outputStream)){
            jsonFactory.writeStartObject("mock");

            writeMockAuthor(jsonFactory);
            writeMockContent(jsonFactory);
            writeMockComment(jsonFactory);

            jsonFactory.writeEndObject();
        }

    }

    private void writeMockAuthor(JsonGenerator jsonGenerator) throws IOException {
        jsonGenerator.writeObjectFieldStart("author");

        if (this.authorModelList != null && !this.authorModelList.isEmpty()) {
            AuthorModel authorModel = null;
            jsonGenerator.writeArrayFieldStart("list");
            for (AuthorModel anAuthorModelList : this.authorModelList) {
                authorModel = anAuthorModelList;
                if (authorModel == null) {
                    continue;
                }

                jsonGenerator.writeStartObject();

                jsonGenerator.writeNumberField("authorId", authorModel.getAuthorId());
                jsonGenerator.writeNumberField("authorAge", authorModel.getAuthorAge());
                jsonGenerator.writeNumberField("authorLevel", authorModel.getAuthorLevel());
                jsonGenerator.writeStringField("authorAddr", authorModel.getAuthorAddr());

                StringBuilder friends = new StringBuilder();
                for (int j = 0; j < authorModel.getFriends().size(); j++) {
                    friends.append(authorModel.getFriends().get(j)).append(" ");
                }

                jsonGenerator.writeStringField("friends", friends.toString());

                jsonGenerator.writeEndObject();

            }

            jsonGenerator.writeEndArray();
        }

        jsonGenerator.writeEndObject();
    }

    private void writeMockContent(JsonGenerator jsonGenerator) throws IOException {
        jsonGenerator.writeObjectFieldStart("content");

        if (this.contentModelList != null && !this.contentModelList.isEmpty()) {

            ContentModel contentModel = null;

            jsonGenerator.writeArrayFieldStart("list");

            for (ContentModel aContentModelList : this.contentModelList) {

                contentModel = aContentModelList;

                if (contentModel == null) {
                    continue;
                }

                jsonGenerator.writeStartObject();

                jsonGenerator.writeNumberField("contentId", contentModel.getContentId());
                jsonGenerator.writeNumberField("authorId", contentModel.getAuthorId());
                jsonGenerator.writeNumberField("commentSize", contentModel.getCommentSize());
                jsonGenerator.writeStringField("content", contentModel.getText());

                StringBuilder commentIds = new StringBuilder();

                for (int j = 0; j < contentModel.getCommentIds().size(); j++) {
                    commentIds.append(contentModel.getCommentIds().get(j));
                }

                jsonGenerator.writeStringField("commentIds", commentIds.toString());

                jsonGenerator.writeEndObject();
            }

            jsonGenerator.writeEndArray();
        }

        jsonGenerator.writeEndObject();
    }

    private void writeMockComment(JsonGenerator jsonGenerator) throws IOException {

        jsonGenerator.writeObjectFieldStart("comment");

        if (this.commentModelList != null && !this.commentModelList.isEmpty()) {

            CommentModel commentModel = null;

            jsonGenerator.writeArrayFieldStart("list");

            for (CommentModel commentModel1 : this.commentModelList) {

                commentModel = commentModel1;

                if (commentModel == null) {
                    continue;
                }

                jsonGenerator.writeStartObject();

                jsonGenerator.writeNumberField("commentId", commentModel.getCommentId());
                jsonGenerator.writeNumberField("authorId", commentModel.getAuthorId());
                jsonGenerator.writeNumberField("contentId", commentModel.getOfContentId());
                jsonGenerator.writeStringField("commentContent", commentModel.getContent());

                jsonGenerator.writeEndObject();
            }

            jsonGenerator.writeEndArray();
        }

        jsonGenerator.writeEndObject();

    }

}
