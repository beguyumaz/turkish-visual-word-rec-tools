package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import application.PsiLingTurk;

public class WordListDialog extends Dialog {

	public WordListDialog(Shell parent) {
		super(parent);
		// TODO Auto-generated constructor stub
	}
	private Shell shlWordList;
	private Table table;

	public void open() throws IOException {
		shlWordList = new Shell(getParent());
		shlWordList.setMinimumSize(new Point(500, 400));
		shlWordList.setSize(443, 400);
		shlWordList.setText("Word List");

		draw(shlWordList); // Contents of Dialog
		shlWordList.pack();
		shlWordList.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		table = new Table(shlWordList, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		
		TableColumn tblclmnWord = new TableColumn(table, SWT.NONE);
		tblclmnWord.setResizable(false);
		tblclmnWord.setWidth(106);
		tblclmnWord.setText("Word");
		
		TableColumn tblclmnType = new TableColumn(table, SWT.NONE);
		tblclmnType.setResizable(false);
		tblclmnType.setWidth(72);
		tblclmnType.setText("Type");
		
		TableColumn tblclmnFrequency = new TableColumn(table, SWT.CENTER);
		tblclmnFrequency.setResizable(false);
		tblclmnFrequency.setWidth(97);
		tblclmnFrequency.setText("Frequency");
		
		TableColumn tblclmnOrtho = new TableColumn(table, SWT.CENTER);
		tblclmnOrtho.setResizable(false);
		tblclmnOrtho.setWidth(97);
		tblclmnOrtho.setText("ON");
		
		TableColumn tblclmnOld20 = new TableColumn(table, SWT.CENTER);
		tblclmnOld20.setResizable(false);
		tblclmnOld20.setWidth(97);
		tblclmnOld20.setText("Old20");
		
		//obtain an Iterator for Collection
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(
						WordListDialog.class.getResourceAsStream("word_list.txt"), "UTF-8"));
		String line;
		while ((line=reader.readLine()) != null)
		{
			if(line.length()>0 )
			{
				String[] words = line.split(" ");
				TableItem item = new TableItem(this.table, SWT.NONE);
				item.setText(0, words[0]);
				item.setText(1, words[1]);
				item.setText(2, words[2]);
				item.setText(3, words[3]);
				item.setText(4, words[4]);
			}
		}
		
		shlWordList.open();

		Display display = getParent().getDisplay();
		while (!shlWordList.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		
	}

	private void draw(Shell shell) {
		
	}

}
