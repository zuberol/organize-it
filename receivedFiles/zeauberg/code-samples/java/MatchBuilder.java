

class MatchBuilder {
	public static void main(String [] args) {
		String testCase = "public static pl.jzuber.songmate.model.challenges.SendMeSongChallenge$SendMeSongChallengeBuilder pl.jzuber.songmate.model.challenges.SendMeSongChallenge.builder()";	
		
		String challengeName = "pl.jzuber.songmate.model.challenges.SendMeSongChallenge";

		String builderP = challengeName + ".builder()$";
		builderP = ".*" + builderP.replace(".", "\\.").replace("(","\\(").replace(")","\\)");

		System.out.println(challengeName);
		System.out.println(builderP);
		System.out.println(testCase.matches(builderP));
		
		System.out.println("kokotkot".matches(".*abc$"));

		System.out.println(".");



		final String mName = "public pl.jzuber.songmate.model.challenges.Challenge$ChallengeBuilder pl.jzuber.songmate.model.challenges.Challenge$ChallengeBuilder.challengeName(java.lang.String)";

		System.out.println(mName.matches(".*challengeName\\(.*\\)$"));





	}
}
