public class Phrases {
    // shared across the round( which is set by the Host)
    private static String gamePhrase; // static field

    //2. per round working string with underscores for letters
    private static String playingPhrase; // object

    // host provides the phrase assign gamePhrase and build playingPhrase
    //Constructors

    // A. the host will set the gamePhrase first, then we can build from there
    public Phrases() {
        if(gamePhrase == null) {
            gamePhrase = "";
        }
    }

    // getters and setters for the shared phrase
    public static void setGamePhrase(String phrase){
        gamePhrase = phrase != null ? phrase : "";
    }

    public static String getGamePhrase() {
        return playingPhrase;
    }
    // for the UI of the turn
    public String getPlayingPhrase() {
        return playingPhrase;
    }

    //true when there are no underscores left; the phrase is fully revealed
    public boolean isSolved() {
        return playingPhrase.indexOf('_') == -1;
    }

    /**
     * Reveal all occurrences of the given letter
     * @param input one character string(letter) if longer than one, throws 'MultipleLettersException'
     *              if not a letter(number/symbol), we throw IllegalArgumentException(Turn should catch)
     * @return how many letters were revealed (0 = no matches; >0 = at least one match
     * @throws MultipleLettersException if input length > 1 (after trim)
     */

    public int findLetters(String input) throws MultipleLettersException {
        if (input == null) {
            throw new IllegalArgumentException("Please enter a single letter(A-Z)");
        }

        String s = input.trim();
        if (s.length() == 0) {
            throw new IllegalArgumentException("Please enter a single letter(A-Z).");
        }
        if (s.length() > 1) {
            // throw our custom exception
            throw new MultipleLettersException();
        }

        char guess = s.charAt(0);
        if (!Character.isLetter(guess)) {
            // catch numbers or symbols
            throw new IllegalArgumentException("Please enter a single letter (A-Z)");
        }

        // compare without case sensitivity
        char guessLower = Character.toLowerCase(guess);

        // work with a mutable array of chars for playingPhrase
        char[] work = playingPhrase.toCharArray();
        int countRevealed = 0;

        // walk the gamePhrase and reveal matches in playingPhrase
        for (int i = 0; i < gamePhrase.length(); i++) {
            char gp = gamePhrase.charAt(i);
            if (Character.isLetter(gp)) {
                if (Character.toLowerCase(gp) == guessLower && work[i] == '_') {
                    // revela the actual letter from the game phrase (preserves its orignal case
                    work[i] = gp;
                    countRevealed++;
                }
            }
        }

        // update the working mashed phrase
        playingPhrase = new String(work);
        return countRevealed;

        // Helpers
        // build the masked version : letters -> '_', spaces ( and other non letters) kept as-is

        }
        private static String maskPhrase(String phrase){
        if(phrase == null) return "";
        StringBuilder sb = new StringBuilder(phrase.length());

        for(int i = 0; i < phrase.length(); i++){
            char c = phrase.charAt(i);
            if(Character.isLetter(c)) {
                sb.append('_');
            } else {
                // keep spaces and punctuation
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
