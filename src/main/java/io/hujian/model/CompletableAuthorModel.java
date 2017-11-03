package io.hujian.model;

import java.util.List;

/**
 * Created by hujian06 on 2017/11/3.
 *
 * the completable author
 */
public class CompletableAuthorModel extends AuthorModel{

    private List<AuthorModel> friendsCompletableInfo;
    private List<CompletableContentModel> contentModelList; // the content list of this author including the comments

    public List<AuthorModel> getFriendsCompletableInfo() {
        return friendsCompletableInfo;
    }

    public void setFriendsCompletableInfo(List<AuthorModel> friendsCompletableInfo) {
        this.friendsCompletableInfo = friendsCompletableInfo;
    }

    public List<CompletableContentModel> getContentModelList() {
        return contentModelList;
    }

    public void setContentModelList(List<CompletableContentModel> contentModelList) {
        this.contentModelList = contentModelList;
    }
}
