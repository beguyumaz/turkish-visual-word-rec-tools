package ui;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.custom.ScrolledComposite;

public class AboutDialog extends Dialog {

	protected Object result;
	protected Shell shlAbout;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public AboutDialog(Shell parent) {
		super(parent);
		setText("Kelimetrik About");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlAbout.open();
		shlAbout.layout();
		Display display = getParent().getDisplay();
		while (!shlAbout.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shlAbout = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.RESIZE);
		shlAbout.setMinimumSize(new Point(800, 600));
		shlAbout.setSize(800, 688);
		shlAbout.setText("About");
		
		Label lblKelimetrikAPsycholinguistic = new Label(shlAbout, SWT.CENTER);
		lblKelimetrikAPsycholinguistic.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		lblKelimetrikAPsycholinguistic.setBounds(0, 10, 784, 21);
		lblKelimetrikAPsycholinguistic.setText("KelimetriK: A psycholinguistic tool of Turkish");
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(shlAbout, SWT.BORDER | SWT.V_SCROLL);
		scrolledComposite.setBounds(0, 37, 774, 602);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		Composite composite = new Composite(scrolledComposite, SWT.NONE);
		
		Label label = new Label(composite, SWT.NONE);
		label.setSize(107, 21);
		label.setText("Introduction");
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		
		Label label_1 = new Label(composite, SWT.WRAP);
		label_1.setLocation(0, 27);
		label_1.setSize(753, 157);
		label_1.setText("Selection of appropriate words for a fully controlled word stimuli set is an essential component for conducting an effective psycholinguistic studies (Perea, & Polatsek, 1998; Bowers, Davis, & Hanley, 2004). For example, if the word stimuli set of a visual word recognition study is full of high frequency words, this may create a bias on the behavioral scores and would lead to incorrect inferences about the hypothesis. Thus, experimenters who are intended to work with any kind of verbal stimuli should consider such linguistic variables to obtain reliable results.\r\n\r\nKelimetriK is a query-based software program designed to demonstrate several lexical variables and orthographic statistics of words. As shown in Figure X, the user-friendly interface of KelimetriK is an easy-to-use software developed to be a helpful source experimenters who are preparing verbal stimuli sets for psycholinguistic studies. KelimetriK provides information about several lexical properties of word-frequency, neighborhood size, orthographic similarity and relatedness. KelimetriK\u2019s counterparts in other language are N-watch in English (Davis, 2005) and BuscaPalabras in Spanish (Davis, & Perea, 2005).");
		
		Label label_2 = new Label(composite, SWT.NONE);
		label_2.setLocation(0, 190);
		label_2.setSize(753, 21);
		label_2.setText("The lexical variables in KelimetriK Software");
		label_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_2.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		
		Label label_3 = new Label(composite, SWT.WRAP);
		label_3.setBounds(0, 228, 753, 546);
		label_3.setText("Output lexical variables (and orthographic statistics) in KelimetriK are word-frequency, bigram and tri-gram frequency and average frequency values, orthographic neighborhood size (Coltheart\u2019s N), orthographic Levensthein distance 20 (OLD20) and transposed letter and subset/superset similarity.\r\n\r\nWord frequency is a value that describes of how many times a word occurred in a given text. Research shows that there is a consistent logarithmic relationship between reaction time and word\u2019s frequency score; the impact of effect is higher for smaller frequencies words that the effect gets smaller on higher frequency scores (Davis, 2005).\r\n\r\nBi-grams and tri-grams are are obtained by decomposing a word (string of tokens) into sequences of two and three number of neighboring elements (Manning, & Sch\u00FCtze, 1999). For example, the Turkish word \u201Ckule\u201D (tower in English) can be decomposed into three different bigram sets (\u201Cku\u201D, \u201Cul\u201D, \u201Cle\u201D). Bigram/trigram frequency values are obtained by counting how many same letter (four in this case) words will start with first bigram set (e.g. \u201Cku\u201D), how many words have second bigram set in the middle (e.g. \u201Cul\u201D) in the middle, and how many words will end with last bigram set (\u201Cle\u201D) in a given lexical word database. The trigrams for the word \u201Ckule\u201D are \u201Ckul\u201D and \u201Cule\u201D. Average bi-gram/tri-gram frequency is obtained by adding a word\u2019s entire bi-gram/ tri-gram frequencies and then dividing it by number of bigrams (\u201Ckule\u201D consist of three bigrams).\r\n\r\nOrthographic neighborhood size (Coltheart\u2019s N) refers to number of words that can be obtained from a given lexical database word list by substituting a single letter of a word (Coltheart et al, 1977). For example, orthographic neighborhood size of the word \u201Ckule\u201D is 9 if searched on KelimetriK (\u201C\u015Fule\u201D, \u201Ckula\u201D, \u201Ckulp\u201D, \u201Cfule\u201D, \u201Ckale\u201D, \u201Ck\u00F6le\u201D, \u201Ckele\u201D, \u201Ckile\u201D, \u201Cku\u015Fe\u201D). A word\u2019s orthographic neighborhood size could influence behavioral performance in visual word recognition tasks of lexical decision, naming, perceptual identification, and semantic categorization (Perea, & Polatsek, 1998).\r\n\r\nOrthographic Levensthein distance 20 (OLD20) of a word is the average of 20 most close words in the unit of Levensthein distance (Yarkoni, Balota, & Yap, 2008). Levensthein distance between the two strings of letters is obtained by counting the minimum number of operations (substitution, deletion or insertion) required while passing from one letter string to the other (Levenshthein, 1966). Behavioral studies show that, OLD20 is negatively correlated with orthographic neighborhood size (r=-561) and positively correlated with word-length (r=868) for English words (Yarkoni, Balota, & Yap, 2008). Moreover, OLD20 explains more variance on visual word recognition scores than orthographic neighborhood size and word length (Yarkoni, Balota, & Yap, 2008).\r\n\r\nOrthographic similarity between two words means they are the neighbors of each other like the words \u201Cal\u0131n\u201D (forehead in English) and \u201Calan\u201D (area in English). Transposed letter (TL) and subset/superset are the two most common similarities in the existing literature  (Davis, 2005). TL similiarity is the case when the two letters differ from each other based on a single pair of adjacent letters as in the Turkish words of \u201Cesen\u201D (blustery) and \u201Cesne\u201D (yawn). Studies have shown that TL similarity may facilitate detection performance on naming and lexical decision task (Andrews, 1996). Subset/Superset similarity occurs when there is an embedded word in a given input word such as \u201Cs\u00FCt\u201D (subset: milk in Turkish) \u201Cs\u00FCtun\u201D (superset: pillar in Turkish). Presence of a subset in a word in a stimuli set may influence the subject\u2019s reading performance, hence may create a confounding factor on the behavioral results (Bowers, Davis, & Hanley, 2005).");
		
		Label lblAndrewsLexical = new Label(composite, SWT.NONE);
		lblAndrewsLexical.setLocation(0, 798);
		lblAndrewsLexical.setSize(753, 296);
		lblAndrewsLexical.setText("Andrews (1996). Lexical retrieval and selection processes: Effects of transposed-letter confusability, Journal of Memory and Language 35, 775\u2013800\r\n\r\nBowers, J. S., Davis, C. J., & Hanley, D. A. (2005). References automatic semantic activation of embedded words: Is there a \u2018\u2018hat\u2019\u2019 in \u2018\u2018that\u2019\u2019? Journal of Memory and Language, 52, 131-143.\r\n\r\nColtheart, M., Davelaar, E., Jonasson, J. T., & Besner, D. (1977). Access to the internal lexicon. Attention and Performance, 6, 535-555.\r\n\r\nDavis, C. J. (2005). N-Watch: A program for deriving neighborhood size and other psycholinguistic statistics. Behavior Research Methods, 37, 65-70.\r\n\r\nDavis, C. J., & Parea, M. (2005). BuscaPalabras: A program for deriving orthographic and phonological neighborhood statistics and other psycholinguistic indices in Spanish. Behavior Research Methods, 37, 665-671.\r\n\r\nLevenshtein, V. I. (1966, February). Binary codes capable of correcting deletions, insertions and reversals. In Soviet physics doklady (Vol. 10, p. 707).\r\n\r\nManning, C. D., & Sch\u00FCtze, H. (1999). Foundations of statistical natural language processing. MIT press.\r\n\r\nPerea, M., & Pollatsek, A. (1998). The effects of neighborhood frequency in reading and lexical decision. Journal of Experimental Psychology, 24, 767-779.\r\n\r\nYarkoni, T., Balota, D., & Yap, M. (2008). Moving beyond Coltheart\u2019s N: A new measure of orthographic similarity. Psychonomic Bulletin & Review, 15(5), 971-979.\r\n");
		
		Label label_4 = new Label(composite, SWT.NONE);
		label_4.setBounds(0, 771, 753, 21);
		label_4.setText("References");
		label_4.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_4.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		scrolledComposite.setContent(composite);
		scrolledComposite.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));

	}
}
