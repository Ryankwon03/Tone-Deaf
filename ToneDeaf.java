package code;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class ToneDeaf {

	// Editable Variables
	private static int wordsPerRound = 5;
	private static final String[] DATA_SET = {
			new File("src/data/hsk_one").getAbsolutePath(),
			new File("src/data/hsk_two").getAbsolutePath(),
			new File("src/data/hsk_three").getAbsolutePath(),
			new File("src/data/player_set").getAbsolutePath(),
			new File("src/data/statistics").getAbsolutePath()};

	// Variables
	private JFrame frame;
	private static int levelVal;
	private static JLabel word;
	private static Word currentWord;
	private static String input;
	private static JLabel correct;
	private static int count;
	private static int life;
	private static JPanel heartOne;
	private static JPanel heartTwo;
	private static JLabel time;
	private static Timer timer;
	private static int seconds; // in 1/100 of a second
	private static JPanel gameOver;
	private static JPanel oneHearts;
	private static JPanel twoHearts;
	private static JPanel zeroHearts;
	private static JLabel viewTime;
	private static JLabel totalGames;
	private static JLabel quickestOne;
	private static JLabel quickestTwo;
	private static JLabel quickestThree;
	private static JLabel totalLivesLost;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ToneDeaf window = new ToneDeaf();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public ToneDeaf() {
		initialize();
	}

	private void initialize() {
		
		// Frame
			frame = new JFrame("Tone Deaf");
			frame.setResizable(false);
			frame.setBounds(100, 100, 850, 622);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Start Panel
			JPanel start = new JPanel();
			frame.getContentPane().add(start, BorderLayout.CENTER);
			start.setLayout(new BorderLayout(0, 0));
	
			JLabel startImage = new JLabel("");
			startImage.setIcon(new ImageIcon(ToneDeaf.class.getResource("/images/start.png")));
			start.add(startImage, BorderLayout.CENTER);

		// Menu Panel
			JPanel menu = new JPanel();
			menu.setLayout(null);
	
			JPanel playerSet = new JPanel();
			playerSet.setBounds(574, 0, 276, 600);
			playerSet.setOpaque(false);
			menu.add(playerSet);
	
			JPanel stats = new JPanel();
			stats.setBounds(269, 0, 304, 594);
			stats.setOpaque(false);
			menu.add(stats);
	
			JPanel levels = new JPanel();
			levels.setBounds(0, 0, 269, 600);
			levels.setOpaque(false);
			menu.add(levels);
	
			JLabel menuImage = new JLabel("");
			menuImage.setIcon(new ImageIcon(ToneDeaf.class.getResource("/images/menu.png")));
			menuImage.setBounds(0, 0, 850, 600);
			menu.add(menuImage);

		// Level Panel
			JPanel level = new JPanel();
			level.setLayout(null);
	
			JPanel returnToMenu = new JPanel();
			returnToMenu.setBounds(687, 0, 163, 59);
			returnToMenu.setOpaque(false);
			level.add(returnToMenu);
	
			JPanel levelOne = new JPanel();
			levelOne.setBounds(0, 0, 850, 135);
			levelOne.setOpaque(false);
			level.add(levelOne);
	
			JPanel levelTwo = new JPanel();
			levelTwo.setBounds(0, 135, 850, 157);
			levelTwo.setOpaque(false);
			level.add(levelTwo);
	
			JPanel levelThree = new JPanel();
			levelThree.setBounds(0, 293, 850, 163);
			levelThree.setOpaque(false);
			level.add(levelThree);
	
			JPanel levelFour = new JPanel();
			levelFour.setBounds(0, 456, 850, 144);
			levelFour.setOpaque(false);
			level.add(levelFour);
	
			JLabel levelsImage = new JLabel("");
			levelsImage.setIcon(new ImageIcon(ToneDeaf.class.getResource("/images/level_selection.png")));
			levelsImage.setBounds(0, 0, 850, 600);
			level.add(levelsImage);

		// GameOver Panel
			gameOver = new JPanel();
			gameOver.setLayout(null);
	
			oneHearts = new JPanel();
			oneHearts.setBackground(Color.WHITE);
			oneHearts.setBounds(390, 217, 136, 125);
			gameOver.add(oneHearts);
			oneHearts.setVisible(false);
	
			twoHearts = new JPanel();
			twoHearts.setBackground(Color.WHITE);
			twoHearts.setBounds(462, 261, 74, 81);
			gameOver.add(twoHearts);
			twoHearts.setVisible(false);
	
			zeroHearts = new JPanel();
			zeroHearts.setBackground(Color.WHITE);
			zeroHearts.setBounds(314, 261, 222, 81);
			gameOver.add(zeroHearts);
			zeroHearts.setVisible(false);
	
			viewTime = new JLabel("");
			viewTime.setFont(new Font("Courier New", Font.PLAIN, 40));
			viewTime.setHorizontalAlignment(SwingConstants.CENTER);
			viewTime.setBounds(286, 65, 273, 73);
			gameOver.add(viewTime);
	
			JLabel gameOverImage = new JLabel("");
			gameOverImage.setIcon(new ImageIcon(ToneDeaf.class.getResource("/images/game_over.png")));
			gameOverImage.setBounds(0, 0, 850, 600);
			gameOver.add(gameOverImage);
	
			JPanel backToLevels = new JPanel();
			backToLevels.setBounds(37, 470, 244, 59);
			gameOver.add(backToLevels);

		// GamePanel Panel
			JPanel gamePanel = new JPanel();
			gamePanel.setLayout(null);
	
			word = new JLabel("开始");
			word.setFont(new Font("Courier New", Font.PLAIN, 49));
			word.setHorizontalAlignment(SwingConstants.CENTER);
			word.setBounds(178, 121, 460, 103);
			gamePanel.add(word);
	
			correct = new JLabel("");
			correct.setFont(new Font("Courier New", Font.PLAIN, 25));
			correct.setHorizontalAlignment(SwingConstants.CENTER);
			correct.setBounds(718, 252, 132, 58);
			gamePanel.add(correct);
	
			time = new JLabel("");
			time.setFont(new Font("Courier New", Font.PLAIN, 25));
			time.setHorizontalAlignment(SwingConstants.LEADING);
			time.setBounds(84, 6, 115, 52);
			gamePanel.add(time);
	
			heartOne = new JPanel();
			heartOne.setBackground(Color.WHITE);
			heartOne.setBounds(786, 0, 64, 58);
			gamePanel.add(heartOne);
			heartOne.setVisible(false);
	
			heartTwo = new JPanel();
			heartTwo.setBackground(Color.WHITE);
			heartTwo.setBounds(729, 0, 57, 58);
			gamePanel.add(heartTwo);
			heartTwo.setVisible(false);
	
			JLabel gameImage = new JLabel("");
			gameImage.setIcon(new ImageIcon(ToneDeaf.class.getResource("/images/game_play.png")));
			gameImage.setBounds(0, 0, 850, 600);
			gamePanel.add(gameImage);
	
			JPanel toneOne = new JPanel();
			toneOne.setBounds(90, 314, 127, 286);
			gamePanel.add(toneOne);
	
			JPanel toneTwo = new JPanel();
			toneTwo.setBounds(216, 314, 132, 286);
			gamePanel.add(toneTwo);
	
			JPanel toneThree = new JPanel();
			toneThree.setBounds(348, 312, 127, 288);
			gamePanel.add(toneThree);
	
			JPanel toneFour = new JPanel();
			toneFour.setBounds(474, 314, 137, 286);
			gamePanel.add(toneFour);
	
			JPanel toneFive = new JPanel();
			toneFive.setBounds(612, 314, 127, 286);
			gamePanel.add(toneFive);

		// Stats Panel
			JPanel stat = new JPanel();
			stat.setLayout(null);
	
			totalGames = new JLabel("");
			totalGames.setFont(new Font("Courier New", Font.PLAIN, 40));
			totalGames.setHorizontalAlignment(SwingConstants.CENTER);
			totalGames.setBounds(324, 66, 474, 66);
			stat.add(totalGames);
	
			quickestOne = new JLabel("");
			quickestOne.setFont(new Font("Courier New", Font.PLAIN, 15));
			quickestOne.setHorizontalAlignment(SwingConstants.CENTER);
			quickestOne.setBounds(307, 261, 107, 25);
			stat.add(quickestOne);
	
			quickestTwo = new JLabel("");
			quickestTwo.setHorizontalAlignment(SwingConstants.CENTER);
			quickestTwo.setFont(new Font("Courier New", Font.PLAIN, 15));
			quickestTwo.setBounds(497, 261, 107, 25);
			stat.add(quickestTwo);
	
			quickestThree = new JLabel("");
			quickestThree.setHorizontalAlignment(SwingConstants.CENTER);
			quickestThree.setFont(new Font("Courier New", Font.PLAIN, 15));
			quickestThree.setBounds(690, 261, 107, 25);
			stat.add(quickestThree);
	
			totalLivesLost = new JLabel("");
			totalLivesLost.setHorizontalAlignment(SwingConstants.CENTER);
			totalLivesLost.setFont(new Font("Courier New", Font.PLAIN, 40));
			totalLivesLost.setBounds(324, 386, 474, 66);
			stat.add(totalLivesLost);
	
			JLabel statImage = new JLabel("");
			statImage.setIcon(new ImageIcon(ToneDeaf.class.getResource("/images/statistics_show.png")));
			statImage.setBounds(0, 0, 850, 600);
			stat.add(statImage);
	
			JPanel backToMenuStat = new JPanel();
			backToMenuStat.setBounds(0, 0, 170, 58);
			stat.add(backToMenuStat);
	
			JPanel resetStats = new JPanel();
			resetStats.setBounds(703, 0, 147, 54);
			stat.add(resetStats);
	
		// Player Data Set Panel
			JPanel player = new JPanel();
			player.setLayout(null);
	
			JTextArea playerInput = new JTextArea();
			playerInput.setFont(new Font("Courier New", Font.PLAIN, 20));
			playerInput.setText("Test:\n你好 33");
			playerInput.setBounds(201, 112, 426, 353);
			player.add(playerInput);
	
			JLabel playerImage = new JLabel("");
			playerImage.setIcon(new ImageIcon(ToneDeaf.class.getResource("/images/player_words.png")));
			playerImage.setBounds(0, 0, 850, 600);
			player.add(playerImage);
	
			JPanel toMenu = new JPanel();
			toMenu.setBounds(0, 0, 167, 62);
			player.add(toMenu);
	
			JPanel save = new JPanel();
			save.setBounds(683, 511, 136, 52);
			player.add(save);

		// Action Listeners
			toneOne.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					input += "1";
					check();
				}
			});
			frame.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == 49) {
						input += "1";
						check();
					}
				}
			});
			toneTwo.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					input += "2";
					check();
				}
			});
			frame.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == 50) {
						input += "2";
						check();
					}
				}
			});
			toneThree.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					input += "3";
					check();
				}
			});
			frame.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == 51) {
						input += "3";
						check();
					}
				}
			});
			toneFour.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					input += "4";
					check();
				}
			});
			frame.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == 52) {
						input += "4";
						check();
					}
				}
			});
			toneFive.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					input += "5";
					check();
				}
			});
			frame.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == 53) {
						input += "5";
						check();
					}
				}
			});
	
			backToLevels.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					frame.getContentPane().removeAll();
					frame.getContentPane().add(level, BorderLayout.CENTER);
					frame.invalidate();
					frame.validate();
					frame.repaint();
				}
			});
			levels.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					frame.getContentPane().removeAll();
					frame.getContentPane().add(level, BorderLayout.CENTER);
					frame.invalidate();
					frame.validate();
					frame.repaint();
				}
			});
			start.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					frame.getContentPane().removeAll();
					frame.getContentPane().add(menu, BorderLayout.CENTER);
					frame.invalidate();
					frame.validate();
					frame.repaint();
				}
			});
			stats.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					try {
						BufferedReader br = new BufferedReader(new FileReader(DATA_SET[4]));
						totalGames.setText(br.readLine());
						quickestOne.setText("" + ((double) Integer.parseInt(br.readLine()) / 100));
						quickestTwo.setText("" + ((double) Integer.parseInt(br.readLine()) / 100));
						quickestThree.setText("" + ((double) Integer.parseInt(br.readLine()) / 100));
						totalLivesLost.setText(br.readLine());
						br.close();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					frame.getContentPane().removeAll();
					frame.getContentPane().add(stat, BorderLayout.CENTER);
					frame.invalidate();
					frame.validate();
					frame.repaint();
				}
			});
			levelOne.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					levelVal = 1;
					frame.getContentPane().removeAll();
					frame.getContentPane().add(gamePanel, BorderLayout.CENTER);
					frame.invalidate();
					frame.validate();
					frame.repaint();
					init();
				}
			});
			levelTwo.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					levelVal = 2;
					frame.getContentPane().removeAll();
					frame.getContentPane().add(gamePanel, BorderLayout.CENTER);
					frame.invalidate();
					frame.validate();
					frame.repaint();
					init();
				}
			});
			levelThree.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					levelVal = 3;
					frame.getContentPane().removeAll();
					frame.getContentPane().add(gamePanel, BorderLayout.CENTER);
					frame.invalidate();
					frame.validate();
					frame.repaint();
					init();
				}
			});
			levelFour.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					levelVal = 4;
					frame.getContentPane().removeAll();
					frame.getContentPane().add(gamePanel, BorderLayout.CENTER);
					frame.invalidate();
					frame.validate();
					frame.repaint();
					init();
				}
			});
			playerSet.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					frame.getContentPane().removeAll();
					frame.getContentPane().add(player, BorderLayout.CENTER);
					frame.invalidate();
					frame.validate();
					frame.repaint();
					try {
						BufferedReader br = new BufferedReader(new FileReader(DATA_SET[3]));
						String temp;
						temp = br.readLine();
						String input = "";
						while (temp != null) {
							input += temp + "\n";
							temp = br.readLine();
						}
						br.close();
						playerInput.setText(input);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			});
			toMenu.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					frame.getContentPane().removeAll();
					frame.getContentPane().add(menu, BorderLayout.CENTER);
					frame.invalidate();
					frame.validate();
					frame.repaint();
				}
			});
			save.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					try {
						PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(DATA_SET[3])));
						pw.print(playerInput.getText());
						pw.close();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					frame.getContentPane().removeAll();
					frame.getContentPane().add(menu, BorderLayout.CENTER);
					frame.invalidate();
					frame.validate();
					frame.repaint();
				}
			});
			returnToMenu.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					frame.getContentPane().removeAll();
					frame.getContentPane().add(menu, BorderLayout.CENTER);
					frame.invalidate();
					frame.validate();
					frame.repaint();
				}
			});
			backToMenuStat.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					frame.getContentPane().removeAll();
					frame.getContentPane().add(menu, BorderLayout.CENTER);
					frame.invalidate();
					frame.validate();
					frame.repaint();
				}
			});
			resetStats.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					try {
						PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(DATA_SET[4])));
						for (int i = 0; i < 5; i++) {
							pw.println("0");
						}
						pw.close();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					try {
						BufferedReader br = new BufferedReader(new FileReader(DATA_SET[4]));
						totalGames.setText(br.readLine());
						quickestOne.setText("" + ((double) Integer.parseInt(br.readLine()) / 100));
						quickestTwo.setText("" + ((double) Integer.parseInt(br.readLine()) / 100));
						quickestThree.setText("" + ((double) Integer.parseInt(br.readLine()) / 100));
						totalLivesLost.setText(br.readLine());
						br.close();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					frame.getContentPane().removeAll();
					frame.getContentPane().add(stat, BorderLayout.CENTER);
					frame.invalidate();
					frame.validate();
					frame.repaint();
				}
			});
	}

	private void init() {
		count = 0;
		life = 3;
		heartOne.setVisible(false);
		heartTwo.setVisible(false);
		seconds = 0;
		oneHearts.setVisible(false);
		twoHearts.setVisible(false);
		zeroHearts.setVisible(false);
		timer = new Timer(10, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				seconds++;
				time.setText("" + ((double) seconds / 100));
			}
		});
		timer.start();
		nextWord();
	}

	private void nextWord() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(DATA_SET[levelVal - 1]));
			ArrayList<Word> values = new ArrayList<Word>();
			String temp;
			while (true) {
				temp = br.readLine();
				if (temp == null)
					break;
				values.add(new Word(temp));
			}
			br.close();
			int id = (int) (Math.random() * values.size());
			word.setText(values.get(id).val);
			currentWord = values.get(id);
			input = "";
			correct.setText("" + count);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	private void check() {
		if (input.length() == currentWord.len) {
			if (input.equals(currentWord.tones)) {
				count++;
				if (count == wordsPerRound) {
					timer.stop();
					viewTime.setText("" + ((double) seconds / 100));
					if (life == 2)
						twoHearts.setVisible(true);
					if (life == 1)
						oneHearts.setVisible(true);
					frame.getContentPane().removeAll();
					frame.getContentPane().add(gameOver, BorderLayout.CENTER);
					frame.invalidate();
					frame.validate();
					frame.repaint();
					try {
						BufferedReader br = new BufferedReader(new FileReader(DATA_SET[4]));
						int[] stats = new int[5];
						for (int i = 0; i < 5; i++) {
							stats[i] = Integer.parseInt(br.readLine());
						}
						br.close();
						stats[0]++;
						if (seconds < stats[levelVal] || stats[levelVal] == 0&&levelVal!=4) {
							stats[levelVal] = seconds;
						}
						stats[4] += (3 - life);
						PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(DATA_SET[4])));
						for (int i = 0; i < 5; i++) {
							pw.println("" + stats[i]);
						}
						pw.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					nextWord();
				}
			} else {
				life--;
				if (life == 2)
					heartOne.setVisible(true);
				if (life == 1)
					heartTwo.setVisible(true);
				if (life == 0) {
					timer.stop();
					viewTime.setText("Fail");
					zeroHearts.setVisible(true);
					frame.getContentPane().removeAll();
					frame.getContentPane().add(gameOver, BorderLayout.CENTER);
					frame.invalidate();
					frame.validate();
					frame.repaint();
					try {
						BufferedReader br = new BufferedReader(new FileReader(DATA_SET[4]));
						int[] stats = new int[5];
						for (int i = 0; i < 5; i++) {
							stats[i] = Integer.parseInt(br.readLine());
						}
						br.close();
						stats[0]++;
						stats[4] += 3;
						PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(DATA_SET[4])));
						for (int i = 0; i < 5; i++) {
							pw.println("" + stats[i]);
						}
						pw.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				nextWord();
			}
		}
	}
}

class Word {

	public String val;
	public int len;
	public String tones;

	public Word(String in) {
		StringTokenizer st = new StringTokenizer(in);
		val = st.nextToken();
		len = val.length();
		tones = st.nextToken();
	}

}