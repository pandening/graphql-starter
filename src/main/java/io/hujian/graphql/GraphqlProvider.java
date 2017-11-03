package io.hujian.graphql;

import graphql.Scalars;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLList;
import graphql.schema.GraphQLOutputType;
import graphql.schema.GraphQLSchema;
import io.hujian.service.AuthorService;
import io.hujian.service.CommentService;
import io.hujian.service.ContentService;
import org.springframework.stereotype.Component;

import static graphql.schema.GraphQLArgument.newArgument;
import static graphql.schema.GraphQLObjectType.newObject;

/**
 * Created by hujian06 on 2017/11/2.
 *
 * the provider of Graphql-java
 */
@Component
public class GraphqlProvider {

    private AuthorService authorService = new AuthorService();

    private ContentService contentService = new ContentService();

    private CommentService commentService = new CommentService();

    /* basic outPutType */
    private GraphQLOutputType author;
    private GraphQLOutputType content;
    private GraphQLOutputType comment;

    /* richness & completable outPutType */
    private GraphQLOutputType completableAuthor;
    private GraphQLOutputType completableContent;

    private GraphQLSchema schema;

    public GraphqlProvider() {
        /* The Author */
        author = newObject().name("AuthorModel")
                .field(GraphQLFieldDefinition.newFieldDefinition().name("authorId").type(Scalars.GraphQLInt))
                .field(GraphQLFieldDefinition.newFieldDefinition().name("authorAge").type(Scalars.GraphQLInt))
                .field(GraphQLFieldDefinition.newFieldDefinition().name("authorLevel").type(Scalars.GraphQLInt))
                .field(GraphQLFieldDefinition.newFieldDefinition().name("authorAddr").type(Scalars.GraphQLString))
                .field(GraphQLFieldDefinition.newFieldDefinition().name("friends").type(GraphQLList.list(Scalars.GraphQLInt)))
                .build();

        /* The Content */
        content = newObject().name("ContentModel")
                .field(GraphQLFieldDefinition.newFieldDefinition().name("contentId").type(Scalars.GraphQLInt))
                .field(GraphQLFieldDefinition.newFieldDefinition().name("authorId").type(Scalars.GraphQLInt))
                .field(GraphQLFieldDefinition.newFieldDefinition().name("commentSize").type(Scalars.GraphQLInt))
                .field(GraphQLFieldDefinition.newFieldDefinition().name("text").type(Scalars.GraphQLString))
                .field(GraphQLFieldDefinition.newFieldDefinition().name("commentIds").type(GraphQLList.list(Scalars.GraphQLInt)))
                .build();

        /* The Comment */
        comment = newObject().name("CommentModel")
                .field(GraphQLFieldDefinition.newFieldDefinition().name("commentId").type(Scalars.GraphQLInt))
                .field(GraphQLFieldDefinition.newFieldDefinition().name("authorId").type(Scalars.GraphQLInt))
                .field(GraphQLFieldDefinition.newFieldDefinition().name("ofContentId").type(Scalars.GraphQLInt))
                .field(GraphQLFieldDefinition.newFieldDefinition().name("content").type(Scalars.GraphQLString))
                .build();

        /* the completable content */
        completableContent = newObject().name("CompletableContent")
                .field(GraphQLFieldDefinition.newFieldDefinition().name("contentId").type(Scalars.GraphQLInt))
                .field(GraphQLFieldDefinition.newFieldDefinition().name("authorId").type(Scalars.GraphQLInt))
                .field(GraphQLFieldDefinition.newFieldDefinition().name("commentSize").type(Scalars.GraphQLInt))
                .field(GraphQLFieldDefinition.newFieldDefinition().name("text").type(Scalars.GraphQLString))
                .field(GraphQLFieldDefinition.newFieldDefinition().name("commentIds").type(GraphQLList.list(Scalars.GraphQLInt)))
                .field(GraphQLFieldDefinition.newFieldDefinition().name("commentModelList").type(GraphQLList.list(comment)))
                .build();

        /* the completable author information */
        completableAuthor = newObject().name("CompletableAuthor")
                .field(GraphQLFieldDefinition.newFieldDefinition().name("authorId").type(Scalars.GraphQLInt))
                .field(GraphQLFieldDefinition.newFieldDefinition().name("authorAge").type(Scalars.GraphQLInt))
                .field(GraphQLFieldDefinition.newFieldDefinition().name("authorLevel").type(Scalars.GraphQLInt))
                .field(GraphQLFieldDefinition.newFieldDefinition().name("authorAddr").type(Scalars.GraphQLString))
                .field(GraphQLFieldDefinition.newFieldDefinition().name("friends").type(GraphQLList.list(Scalars.GraphQLInt)))
                .field(GraphQLFieldDefinition.newFieldDefinition().name("friendsCompletableInfo").type(GraphQLList.list(author)))
                .field(GraphQLFieldDefinition.newFieldDefinition().name("contentModelList").type(GraphQLList.list(completableContent)))
                .build();

        /* set up the schema */
        schema = GraphQLSchema.newSchema()
                .query(newObject()
                        .name("graphqlQuery")
                        .field(createAuthorField())
                        .field(createContentField())
                        .field(createCommentField())
                        .field(createCompletableContentField())
                        .field(createCompletableAuthorField()))
                .build();
    }

    /**
     * completable author information
     * @return the author
     */
    private GraphQLFieldDefinition createCompletableAuthorField() {
        return GraphQLFieldDefinition.newFieldDefinition()
                .name("completableAuthor")
                .argument(newArgument().name("authorId").type(Scalars.GraphQLInt).build())
                .type(completableAuthor)
                .dataFetcher((DataFetchingEnvironment environment) -> {
                    int authorId = environment.getArgument("authorId");

                    //get the completable info of author by authorId
                    //System.out.println("request for createCompletableAuthorField:" + authorId);

                    return authorService.getCompletableAuthorByAuthorId(authorId);
                }).build();
    }

    private GraphQLFieldDefinition createCompletableContentField() {
        return GraphQLFieldDefinition.newFieldDefinition()
                .name("completableContent")
                .argument(newArgument().name("contentId").type(Scalars.GraphQLInt).build())
                .type(completableContent)
                .dataFetcher((DataFetchingEnvironment environment) -> {
                    int contentId = environment.getArgument("contentId");

                    //get the completable info of the content according to the content id

                    return contentService.getComplableContentByContentId(contentId);
                }).build();
    }

    /**
     * query single author
     * @return the single author's information
     */
    private GraphQLFieldDefinition createAuthorField() {
        return GraphQLFieldDefinition.newFieldDefinition()
                .name("author")
                .argument(newArgument().name("authorId").type(Scalars.GraphQLInt).build())
                .type(author)
                .dataFetcher((DataFetchingEnvironment environment) -> {

                    //get the author id here
                    int authorId = environment.getArgument("authorId");

                    return this.authorService.getAuthorByAuthorId(authorId);
                }).build();

    }

    private GraphQLFieldDefinition createContentField() {
        return GraphQLFieldDefinition.newFieldDefinition()
                .name("content")
                .argument(newArgument().name("contentId").type(Scalars.GraphQLInt).build())
                .type(content)
                .dataFetcher((DataFetchingEnvironment environment) -> {
                    int contentId = environment.getArgument("contentId");

                    return this.contentService.getContentByContentId(contentId);
                }).build();
    }

    private GraphQLFieldDefinition createCommentField() {
        return GraphQLFieldDefinition.newFieldDefinition()
                .name("comment")
                .argument(newArgument().name("commentId").type(Scalars.GraphQLInt).build())
                .type(comment)
                .dataFetcher((DataFetchingEnvironment environment) -> {

                    int commentId = environment.getArgument("commentId");

                    return this.commentService.getCommentByCommentId(commentId);
                }).build();

    }


    public GraphQLOutputType getAuthor() {
        return author;
    }

    public GraphQLOutputType getContent() {
        return content;
    }

    public GraphQLOutputType getComment() {
        return comment;
    }

    public GraphQLSchema getSchema() {
        return schema;
    }

    public AuthorService getAuthorService() {
        return authorService;
    }
}
