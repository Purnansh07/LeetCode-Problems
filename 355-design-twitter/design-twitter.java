class Twitter {

    private static class Tweet {
        int id;
        int time;
        Tweet next;

        Tweet(int id, int time) {
            this.id = id;
            this.time = time;
        }
    }

    private static class User {
        int id;
        Set<Integer> following = new HashSet<>();
        Tweet tweets;

        User(int id) {
            this.id = id;
            following.add(id); // follow yourself
        }
    }

    private final Map<Integer, User> users = new HashMap<>();
    private int timestamp = 0;

    public Twitter() {
    }

    public void postTweet(int userId, int tweetId) {
        User user = getUser(userId);

        Tweet tweet = new Tweet(tweetId, timestamp++);
        tweet.next = user.tweets;
        user.tweets = tweet;
    }

    public List<Integer> getNewsFeed(int userId) {
        User user = getUser(userId);

        PriorityQueue<Tweet> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(b.time, a.time)
        );

        for (int followeeId : user.following) {
            User followee = users.get(followeeId);

            if (followee != null && followee.tweets != null) {
                pq.offer(followee.tweets);
            }
        }

        List<Integer> feed = new ArrayList<>(10);

        while (!pq.isEmpty() && feed.size() < 10) {
            Tweet current = pq.poll();

            feed.add(current.id);

            if (current.next != null) {
                pq.offer(current.next);
            }
        }

        return feed;
    }

    public void follow(int followerId, int followeeId) {
        User follower = getUser(followerId);
        User followee = getUser(followeeId);

        follower.following.add(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
        if (followerId == followeeId) return;

        User follower = users.get(followerId);

        if (follower != null) {
            follower.following.remove(followeeId);
        }
    }

    private User getUser(int userId) {
        return users.computeIfAbsent(userId, User::new);
    }
}