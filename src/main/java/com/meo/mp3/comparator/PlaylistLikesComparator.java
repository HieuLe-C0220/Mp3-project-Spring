package com.meo.mp3.comparator;

import com.meo.mp3.models.songs.Playlist;

import java.util.Comparator;

public class PlaylistLikesComparator implements Comparator<Playlist> {
    @Override
    public int compare(Playlist playlist, Playlist t1) {
        if (playlist.getReview().getLikes() == t1.getReview().getLikes()) {
            return 0;
        } else if (playlist.getReview().getLikes() < t1.getReview().getLikes()) {
            return 1;
        } else return -1;
    }
}
