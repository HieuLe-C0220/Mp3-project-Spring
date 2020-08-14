package com.meo.mp3.services.impl;

import com.meo.mp3.models.interactive.Review;
import com.meo.mp3.models.songs.Song;
import com.meo.mp3.models.users.account.User;
import com.meo.mp3.services.ILikeService;
import com.meo.mp3.services.IUserService;
import com.meo.mp3.services.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
public class LikeServiceImpl implements ILikeService {

    @Autowired
    SongService songService;

    @Autowired
    IUserService userService;

    @Override
    public Song likeASong(Long songId, Long userId) {
        Song song = songService.findById(songId);
        User user = userService.findById(userId);
        Review review = song.getReview();
        Set<User> whoLikes = review.getUserSet();
        if (isLikeBefore(whoLikes, user )) {
            whoLikes.remove(user);
            review.setUserSet(whoLikes);
            review.setLikes(whoLikes.size());
        }
        else {
            whoLikes.add(user);
            review.setUserSet(whoLikes);
            review.setLikes(whoLikes.size());
        }
        song.setReview(review);
        return songService.save(song);
    }

    boolean isLikeBefore(Set<User> whoLikes, User u ) {
        if (whoLikes.size() == 0) {
            return false;
        }
        for (User user : whoLikes)  {
            if (user.getId() == u.getId()) {
                return true;
            }
        };
        return false;
    }
}
