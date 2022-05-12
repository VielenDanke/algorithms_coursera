package part1.union_find.questions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class SocialNetworkConnectivity {

    private final int[] friends;
    private final int[] sz;

    public SocialNetworkConnectivity(int amountOfFriends) {
        friends = new int[amountOfFriends];
        sz = new int[amountOfFriends];

        for (int i = 0; i < amountOfFriends; i++) {
            friends[i] = i;
            sz[i] = 1;
        }
    }

    private List<String> loadDataFromFile(String logFile) throws IOException {
        return Files.readAllLines(Path.of(logFile));
    }

    /**
     *
     * Complexity - O(M * logN) where M - size of log file lines. N - amount of friends
     *
     * @param logFile - path to log file
     * @return timestamp if exists when all friends are connected
     * @throws IOException - occurred if file not found
     */
    public String findMinimumTimestamp(String logFile) throws IOException {
        List<String> data = loadDataFromFile(logFile);

        for (String logLine : data) {
            String[] lineData = logLine.split(" ");

            String timestamp = lineData[0];
            int aFriend = Integer.parseInt(lineData[1]);
            int bFriend = Integer.parseInt(lineData[2]);

            int sizeOfFriendship = union(aFriend, bFriend);

            if (sizeOfFriendship == friends.length) {
                return timestamp;
            }
        }
        return "";
    }

    /**
     *
     * Modified Union operation (returns size of the biggest network size from p or q)
     * Complexity - O(logN)
     *
     * @param p - first friend
     * @param q - second friend
     * @return size of the biggest three of friends from p or q
     */
    public int union(int p, int q) {
        int pid = find(p), qid = find(q);

        if (pid == qid) return sz[pid];
        if (sz[pid] < sz[qid]) {
            sz[qid] += sz[pid];
            friends[pid] = qid;
            return sz[qid];
        } else {
            sz[pid] += sz[qid];
            friends[qid] = pid;
            return sz[pid];
        }
    }

    /**
     *
     * Complexity - O(logN)
     *
     * @param p - find root of friend p with path compression
     * @return root of p friend
     */
    private int find(int p) {
        while (p != friends[p]) {
            friends[p] = friends[friends[p]];
            p = friends[p];
        }
        return p;
    }
}
