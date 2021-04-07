package refdiff.examples;

import java.io.File;

import refdiff.core.RefDiff;
import refdiff.core.cst.CstNode;
import refdiff.core.diff.CstDiff;
import refdiff.parsers.js.JsPlugin;

public class RefDiffExampleJs {

	public static void main(String[] args) throws Exception {
		runExample();
	}

	private static void runExample() throws Exception {
		// This is a temp folder to clone or checkout git repositories.
		File tempFolder = new File("temp");

		// Creates a RefDiff instance configured with the JavaScript plugin.
		try (JsPlugin jsPlugin = new JsPlugin()) {
			RefDiff refDiffJs = new RefDiff(jsPlugin);

			// Clone the angular.js GitHub repo.
			File repo = refDiffJs.cloneGitRepository(
				new File(tempFolder, "berkeTests.git"),
				"https://github.com/sadjad-tavakoli/temp_sample.git");

			CstDiff diffForCommit = refDiffJs.computeDiffForCommit(repo, "f82ae29");
			printRefactorings(diffForCommit);
		}
	}

	private static void printRefactorings(CstDiff diff) {
		for (CstNode node : diff.getChangedEntities()){
			System.out.println(node.toString());
		}
	}
}
