package io.hujian.service;

import io.hujian.DataMocker;
import io.hujian.model.AuthorModel;
import io.hujian.model.CompletableAuthorModel;
import io.hujian.model.CompletableContentModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hujian06 on 2017/11/2.
 *
 * the author service mocker
 */
@Service
public class AuthorService {

    private static ContentService contentService = new ContentService();

    private static final List<AuthorModel> authorModelList = DataMocker.DataHolder.authorModelList; // the data

    /**
     * get the author by the author id
     * @param authorId the author id
     * @return the author model
     */
    public AuthorModel getAuthorByAuthorId(int authorId) {
        AuthorModel authorModel = null;

        for (AuthorModel model : authorModelList) {
            if (model.getAuthorId() == authorId) {
                authorModel = model;
                break;
            }
        }

        if (authorModel == null) {
            authorModel = new AuthorModel();
            authorModel.setAuthorId(-1); // -1 means no match author
        }

        return authorModel;
    }

    public CompletableAuthorModel getCompletableAuthorByAuthorId(int authorId) {
        CompletableAuthorModel completableAuthorModel = new CompletableAuthorModel();
        AuthorModel authorModel = getAuthorByAuthorId(authorId);

        if (authorModel.getAuthorId() == -1) {
            completableAuthorModel.setAuthorId(-1);
            return completableAuthorModel;
        }

        //set the basic information for the author
        completableAuthorModel.setAuthorId(authorId);
        completableAuthorModel.setAuthorAge(authorModel.getAuthorAge());
        completableAuthorModel.setAuthorLevel(authorModel.getAuthorLevel());
        completableAuthorModel.setAuthorAddr(authorModel.getAuthorAddr());
        completableAuthorModel.setFriends(authorModel.getFriends());

        //get the richness information of this author
        // include the friends' information and contents' information

        List<AuthorModel> friendsAuthorList = new ArrayList<>(); // the friends info

        for (int id : authorModel.getFriends()) {
            authorModel = getAuthorByAuthorId(id);
            friendsAuthorList.add(authorModel);
        }

        completableAuthorModel.setFriendsCompletableInfo(friendsAuthorList);

        //get the content list information
        List<CompletableContentModel> completableContentModelList =
                contentService.getCompletableContentsByAuthorId(authorId);

        completableAuthorModel.setContentModelList(completableContentModelList);

        return completableAuthorModel;
    }

}
