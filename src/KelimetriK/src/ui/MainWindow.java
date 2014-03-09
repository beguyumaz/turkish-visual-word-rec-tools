package ui;

import java.awt.Desktop;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import application.PsiLingTurk;

import org.eclipse.swt.events.ArmListener;
import org.eclipse.swt.events.ArmEvent;

public class MainWindow {

	protected Shell shlPsilingturk;
	private Text queryText;
	private Button btnSearch;
	private Table tblBigram;
	private TableColumn tblclmnBigram;
	private TableColumn tblclmnLocationSpecific;
	private TableColumn tblclmnAll;
	private Table tblTrigram;
	private TableColumn tblclmnTrigram;
	private TableColumn tableColumn_1;
	private TableColumn tableColumn_2;
	private Table tblOrtho;
	private TableColumn tblclmnOrthographicNeighbors;
	private Table tblSubsets;
	private TableColumn tblclmnSubsets;
	private Table tblSupersets;
	private TableColumn tblclmnSupersets;
	private Label lblPsilingturk;
	private Label lblType;
	private Label lblOrthocount;
	private Label lblSubsetcount;
	private Label lblSupersetcount;
	private Label label;
	private Label lblTypeValue;
	private Label lblOrthocountValue;
	private Label lblSubsetcountValue;
	private Label lblSupersetcountValue;
	private Label lblFrequency;
	private Label lblFrequencyValue;
	private Label lblTransposed;
	private Label lblTransposedValue;
	private Table tblTrans;
	private TableColumn tblclmnTransposed;
	private static Locale turkishLocale = new Locale("tr", "TR");
	private Label lblOld20;
	private Label lblOld20Value;
	private MenuItem mntmOpenWordList;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainWindow window = new MainWindow();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlPsilingturk.open();
		shlPsilingturk.layout();
		shlPsilingturk.setBackgroundImage(SWTResourceManager.getImage(MainWindow.class, "/ui/bg.jpg"));
		
		Menu menu = new Menu(shlPsilingturk, SWT.BAR);
		shlPsilingturk.setMenuBar(menu);
		
		MenuItem mntmFile_1 = new MenuItem(menu, SWT.CASCADE);
		mntmFile_1.setText("File");
		
		Menu menu_1 = new Menu(mntmFile_1);
		mntmFile_1.setMenu(menu_1);
		
		mntmOpenWordList = new MenuItem(menu_1, SWT.NONE);
		mntmOpenWordList.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				WordListDialog dialog = new WordListDialog(shlPsilingturk);
				try {
					dialog.open();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mntmOpenWordList.setText("Open Word List");
		
		MenuItem mntmQuit = new MenuItem(menu_1, SWT.NONE);
		mntmQuit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlPsilingturk.dispose();
			}
		});
		mntmQuit.setText("Quit");
		
		MenuItem mntmAbout = new MenuItem(menu, SWT.NONE);
		mntmAbout.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				AboutDialog about = new AboutDialog(shlPsilingturk);
				about.open();
			}
		});
		mntmAbout.setText("About");
		while (!shlPsilingturk.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlPsilingturk = new Shell(SWT.SHELL_TRIM & (~SWT.RESIZE));
		shlPsilingturk.setImage(SWTResourceManager.getImage(MainWindow.class, "/ui/icon.png"));
		shlPsilingturk.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shlPsilingturk.setSize(800, 646);
		shlPsilingturk.setText("KelimetriK");
		shlPsilingturk.setLayout(null);
		
		lblPsilingturk = new Label(shlPsilingturk, SWT.NONE);
		lblPsilingturk.setBounds(10, 74, 791, 61);
		lblPsilingturk.setForeground(SWTResourceManager.getColor(75, 0, 130));
		lblPsilingturk.setAlignment(SWT.CENTER);
		lblPsilingturk.setFont(SWTResourceManager.getFont("@FangSong", 42, SWT.BOLD));
		lblPsilingturk.setText("KelimetriK");
		
		
		Label lblQuery = new Label(shlPsilingturk, SWT.SHADOW_IN);
		lblQuery.setBounds(202, 144, 110, 21);
		lblQuery.setForeground(SWTResourceManager.getColor(75, 0, 130));
		lblQuery.setAlignment(SWT.RIGHT);
		lblQuery.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		lblQuery.setText("Enter a word:  ");
		
		queryText = new Text(shlPsilingturk, SWT.BORDER);
		queryText.setBounds(313, 146, 180, 21);
		queryText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode == SWT.CR || e.keyCode == SWT.LF )
					makeQuery(queryText.getText());
			}
		});
		
		btnSearch = new Button(shlPsilingturk, SWT.NONE);
		btnSearch.setBounds(499, 144, 47, 25);
		btnSearch.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				makeQuery(queryText.getText());
			}
		});
		btnSearch.setText("Search");
		
		lblType = new Label(shlPsilingturk, SWT.NONE);
		lblType.setBounds(71, 175, 100, 21);
		lblType.setForeground(SWTResourceManager.getColor(75, 0, 130));
		lblType.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		lblType.setAlignment(SWT.RIGHT);
		
		lblTypeValue = new Label(shlPsilingturk, SWT.NONE);
		lblTypeValue.setBounds(171, 175, 129, 21);
		lblTypeValue.setForeground(SWTResourceManager.getColor(178, 34, 34));
		lblTypeValue.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		
		lblFrequency = new Label(shlPsilingturk, SWT.NONE);
		lblFrequency.setBounds(299, 175, 100, 21);
		lblFrequency.setForeground(SWTResourceManager.getColor(75, 0, 130));
		lblFrequency.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		lblFrequency.setAlignment(SWT.RIGHT);
		
		lblFrequencyValue = new Label(shlPsilingturk, SWT.NONE);
		lblFrequencyValue.setBounds(399, 175, 100, 21);
		lblFrequencyValue.setForeground(SWTResourceManager.getColor(178, 34, 34));
		lblFrequencyValue.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		
		lblOld20 = new Label(shlPsilingturk, SWT.NONE);
		lblOld20.setBounds(526, 175, 100, 21);
		lblOld20.setForeground(SWTResourceManager.getColor(75, 0, 130));
		lblOld20.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		lblOld20.setAlignment(SWT.RIGHT);
		
		lblOld20Value = new Label(shlPsilingturk, SWT.NONE);
		lblOld20Value.setBounds(632, 175, 94, 21);
		lblOld20Value.setForeground(SWTResourceManager.getColor(178, 34, 34));
		lblOld20Value.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		
		tblBigram = new Table(shlPsilingturk, SWT.BORDER | SWT.FULL_SELECTION);
		tblBigram.setBounds(71, 198, 326, 151);
		tblBigram.setHeaderVisible(true);
		tblBigram.setLinesVisible(true);
		
		tblclmnBigram = new TableColumn(tblBigram, SWT.NONE);
		tblclmnBigram.setResizable(false);
		tblclmnBigram.setWidth(75);
		tblclmnBigram.setText("Bigram");
		
		tblclmnLocationSpecific = new TableColumn(tblBigram, SWT.NONE);
		tblclmnLocationSpecific.setResizable(false);
		tblclmnLocationSpecific.setWidth(130);
		tblclmnLocationSpecific.setText("Location Specific");
		
		tblclmnAll = new TableColumn(tblBigram, SWT.NONE);
		tblclmnAll.setResizable(false);
		tblclmnAll.setWidth(117);
		tblclmnAll.setText("All");
		
		tblTrigram = new Table(shlPsilingturk, SWT.BORDER | SWT.FULL_SELECTION);
		tblTrigram.setBounds(400, 198, 326, 151);
		tblTrigram.setLinesVisible(true);
		tblTrigram.setHeaderVisible(true);
		
		tblclmnTrigram = new TableColumn(tblTrigram, SWT.NONE);
		tblclmnTrigram.setResizable(false);
		tblclmnTrigram.setWidth(75);
		tblclmnTrigram.setText("Trigram");
		
		tableColumn_1 = new TableColumn(tblTrigram, SWT.NONE);
		tableColumn_1.setResizable(false);
		tableColumn_1.setWidth(130);
		tableColumn_1.setText("Location Specific");
		
		tableColumn_2 = new TableColumn(tblTrigram, SWT.NONE);
		tableColumn_2.setResizable(false);
		tableColumn_2.setWidth(117);
		tableColumn_2.setText("All");
		
		label = new Label(shlPsilingturk, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setBounds(43, 363, 700, 20);
		
		lblOrthocount = new Label(shlPsilingturk, SWT.NONE);
		lblOrthocount.setBounds(71, 378, 100, 20);
		lblOrthocount.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD));
		lblOrthocount.setForeground(SWTResourceManager.getColor(75, 0, 130));
		lblOrthocount.setAlignment(SWT.RIGHT);
		
		lblOrthocountValue = new Label(shlPsilingturk, SWT.NONE);
		lblOrthocountValue.setBounds(177, 378, 45, 20);
		lblOrthocountValue.setForeground(SWTResourceManager.getColor(178, 34, 34));
		lblOrthocountValue.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD));
		
		lblTransposed = new Label(shlPsilingturk, SWT.NONE);
		lblTransposed.setBounds(228, 378, 96, 20);
		lblTransposed.setForeground(SWTResourceManager.getColor(75, 0, 130));
		lblTransposed.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD));
		lblTransposed.setAlignment(SWT.RIGHT);
		
		lblTransposedValue = new Label(shlPsilingturk, SWT.NONE);
		lblTransposedValue.setBounds(330, 378, 50, 20);
		lblTransposedValue.setForeground(SWTResourceManager.getColor(178, 34, 34));
		lblTransposedValue.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD));
		
		lblSubsetcount = new Label(shlPsilingturk, SWT.NONE);
		lblSubsetcount.setBounds(386, 378, 113, 20);
		lblSubsetcount.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD));
		lblSubsetcount.setForeground(SWTResourceManager.getColor(75, 0, 130));
		lblSubsetcount.setAlignment(SWT.RIGHT);
		
		lblSubsetcountValue = new Label(shlPsilingturk, SWT.NONE);
		lblSubsetcountValue.setBounds(504, 378, 50, 20);
		lblSubsetcountValue.setForeground(SWTResourceManager.getColor(178, 34, 34));
		lblSubsetcountValue.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD));
		
		lblSupersetcount = new Label(shlPsilingturk, SWT.NONE);
		lblSupersetcount.setBounds(558, 378, 116, 20);
		lblSupersetcount.setForeground(SWTResourceManager.getColor(75, 0, 130));
		lblSupersetcount.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD));
		lblSupersetcount.setAlignment(SWT.RIGHT);
		
		lblSupersetcountValue = new Label(shlPsilingturk, SWT.NONE);
		lblSupersetcountValue.setBounds(680, 378, 46, 20);
		lblSupersetcountValue.setForeground(SWTResourceManager.getColor(178, 34, 34));
		lblSupersetcountValue.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD));
		
		tblOrtho = new Table(shlPsilingturk, SWT.BORDER | SWT.FULL_SELECTION);
		tblOrtho.setBounds(71, 404, 151, 121);
		tblOrtho.setHeaderVisible(true);
		tblOrtho.setLinesVisible(true);
		
		tblclmnOrthographicNeighbors = new TableColumn(tblOrtho, SWT.NONE);
		tblclmnOrthographicNeighbors.setResizable(false);
		tblclmnOrthographicNeighbors.setWidth(147);
		tblclmnOrthographicNeighbors.setText("Orthographic Neighbors");
		
		tblTrans = new Table(shlPsilingturk, SWT.BORDER | SWT.FULL_SELECTION);
		tblTrans.setBounds(228, 404, 152, 121);
		tblTrans.setLinesVisible(true);
		tblTrans.setHeaderVisible(true);
		
		tblclmnTransposed = new TableColumn(tblTrans, SWT.NONE);
		tblclmnTransposed.setResizable(false);
		tblclmnTransposed.setWidth(148);
		tblclmnTransposed.setText("Transposed (Adj. Letter)");
		
		tblSubsets = new Table(shlPsilingturk, SWT.BORDER | SWT.FULL_SELECTION);
		tblSubsets.setBounds(386, 404, 168, 121);
		tblSubsets.setHeaderVisible(true);
		tblSubsets.setLinesVisible(true);
		
		tblclmnSubsets = new TableColumn(tblSubsets, SWT.NONE);
		tblclmnSubsets.setResizable(false);
		tblclmnSubsets.setWidth(164);
		tblclmnSubsets.setText("Subsets");
		
		tblSupersets = new Table(shlPsilingturk, SWT.BORDER | SWT.FULL_SELECTION);
		tblSupersets.setBounds(558, 404, 168, 121);
		tblSupersets.setLinesVisible(true);
		tblSupersets.setHeaderVisible(true);
		
		tblclmnSupersets = new TableColumn(tblSupersets, SWT.NONE);
		tblclmnSupersets.setResizable(false);
		tblclmnSupersets.setWidth(164);
		tblclmnSupersets.setText("Supersets");
		
		
		try {
			PsiLingTurk.readWords();
			PsiLingTurk.readFreqs();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	private void clearContents() {
		tblBigram.removeAll();
		tblTrigram.removeAll();
		tblOrtho.removeAll();
		tblSubsets.removeAll();
		tblTrans.removeAll();
		tblSupersets.removeAll();
		lblOrthocountValue.setText("");
		lblFrequencyValue.setText("");
		lblSubsetcountValue.setText("");
		lblSupersetcountValue.setText("");
		lblTransposedValue.setText("");
		lblOld20Value.setText("");
	}

	protected void makeQuery(String query) {
		query = query.toLowerCase(turkishLocale);
		clearContents();
		// type
		lblType.setText("Type: ");
		lblFrequency.setText("Frequency: ");
		lblOld20.setText("Old20:");
		if(PsiLingTurk.getType(query) != null) {
			lblTypeValue.setText(PsiLingTurk.getType(query));
			Double freq = PsiLingTurk.getFreq(query);
			if( freq == null)
				lblFrequencyValue.setText("Not Available");
			else
				lblFrequencyValue.setText(Double.toString(freq));
			
			
			lblOld20Value.setText(Double.toString(PsiLingTurk.calculateOld20(query)));
			
			// bigrams
			List<String> bigrams = PsiLingTurk.getBigrams(query);
			int bigramFreq[] = PsiLingTurk.getBigramFreq(query, bigrams);
			int totalBigramFreq[] = PsiLingTurk.getTotalBigramFreq(query, bigrams);
			for (int i = 0; i < bigramFreq.length; i++) {
				String bigram = bigrams.get(i);
				TableItem item = new TableItem(tblBigram, SWT.NONE);
				item.setText(0, bigram);
				item.setText(1, Integer.toString(bigramFreq[i]));
				item.setText(2, Integer.toString(totalBigramFreq[i]));
			}
			
			// trigrams
			List<String> trigrams = PsiLingTurk.getTrigrams(query);
			int trigramFreq[] = PsiLingTurk.getTrigramFreq(query, trigrams);
			int totalTrigramFreq[] = PsiLingTurk.getTotalTrigramFreq(query, trigrams);
			
			for (int i = 0; i < trigramFreq.length; i++) {
				String trigram = trigrams.get(i);
				TableItem item = new TableItem(tblTrigram, SWT.NONE);
				item.setText(0, trigram);
				item.setText(1, Integer.toString(trigramFreq[i]));
				item.setText(2, Integer.toString(totalTrigramFreq[i]));
			}
			
			
			// othographic words
			List<String> orthoList = PsiLingTurk.getOrthographic(query);
			lblOrthocount.setText("Total Count:");
			lblOrthocountValue.setText(Integer.toString(orthoList.size()));
			for (String orthoWord : orthoList) {
				TableItem item = new TableItem(tblOrtho, SWT.NONE);
				item.setText(0, orthoWord);
			}
			
			List<String> transList = PsiLingTurk.getTransposed(query);
			lblTransposed.setText("Total Count:");
			lblTransposedValue.setText(Integer.toString(transList.size()));
			for (String transWord : transList) {
				TableItem item = new TableItem(tblTrans, SWT.NONE);
				item.setText(0, transWord);
			}
			
			
			
			List<String> subsets = PsiLingTurk.getSubsets(query);
			lblSubsetcount.setText("Total Count:");
			lblSubsetcountValue.setText(Integer.toString(subsets.size()));
			for (String subset: subsets) {
				TableItem item = new TableItem(tblSubsets, SWT.NONE);
				item.setText(0, subset);
			}
			
			List<String> supersets = PsiLingTurk.getSupersets(query);
			lblSupersetcount.setText("Total Count:");
			lblSupersetcountValue.setText(Integer.toString(supersets.size()));
			for (String superset: supersets) {
				TableItem item = new TableItem(tblSupersets, SWT.NONE);
				item.setText(0, superset);
			}
		}
		else
		{
			lblTypeValue.setText("Not Available");
		}
		
	}
}
