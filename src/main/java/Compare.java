import java.util.logging.Logger;

/**
 * @author dmifed
 */

public class Compare {
    Logger log = Logger.getAnonymousLogger();

    public static void main(String[] args) {
        Compare compare = new Compare();
        System.out.println(compare.wordsSimilarityCompare("TOOL", "tool"));
    }

    LevelWordSimilariry wordsSimilarityCompare(String origin, String checking){
        origin = origin.toLowerCase();
        checking = checking.toLowerCase();
        if(origin.equals(checking)){
            log.info(origin + "=" + checking);
            return LevelWordSimilariry.FULL;
        }
        if(checking.length() < 3){
            log.info(checking + " : has less 3 symbols");
            return LevelWordSimilariry.NONE;

        }
        int minLengthOfSamePart = origin.length()/2;
        if(minLengthOfSamePart%2 != 0) ++minLengthOfSamePart;
        if(minLengthOfSamePart > checking.length()){
            log.info(checking + " is less tha half of : " + origin);
            return LevelWordSimilariry.NONE;
        }
        for(int i = 0; i <= minLengthOfSamePart; i++){
            String subOrigin = origin.substring(i);

            char startSeq = subOrigin.charAt(0);
            for(int j = 0; j < checking.length(); j++){
                if(checking.charAt(j) == startSeq){
                    if(subOrigin.equals(checking.substring(j))) return LevelWordSimilariry.PARTIALLY;
                    int charPositionInSubOrigin = 1;
                    int count = 0;
                    for(int c = j+1; c < subOrigin.length() - minLengthOfSamePart || c < checking.length()-j; c++){
                        if(checking.charAt(c) != subOrigin.charAt(charPositionInSubOrigin)){
                            count = 0;
                            if(charPositionInSubOrigin >= minLengthOfSamePart){
                                log.info(charPositionInSubOrigin + " >= " + minLengthOfSamePart);
                                return LevelWordSimilariry.PARTIALLY;
                            }

                        }else {
                            ++count;
                            ++charPositionInSubOrigin;
                        }
                    }
                    if (count >= minLengthOfSamePart-1) return LevelWordSimilariry.PARTIALLY;
                }
            }
        }
        log.info("all check done");
        return LevelWordSimilariry.NONE;
    }


}
