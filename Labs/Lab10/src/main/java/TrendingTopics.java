import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * A class for identifying trending topics on a social media platform.
 */
public class TrendingTopics {

    /**
     * Counts the occurrences of each topic in the provided list.
     *
     * @param topics a List of Strings representing social media topics
     * @return a Map where each key is a distinct topic (String) and
     *         each value is the number of times that topic appears in
     *         the input list (Long)
     */
    public Map<String, Long> countTopics(List<String> topics) {
        return topics.stream()
                .collect(Collectors.groupingBy(
                        topic -> topic,
                        Collectors.counting()
                ));
    }
}
