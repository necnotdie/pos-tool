package pos.tools;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class AES extends JFrame
	implements ActionListener
{

	/**
	 * @param args
	 */
	private int[][] S_box = 
	{
			{ 99,124,119,123,242,107,111,197, 48,  1,103, 43,254,215,171,118},
			{202,130,201,125,250, 89, 71,240,173,212,162,175,156,164,114,192},
			{183,253,147, 38, 54, 63,247,204, 52,165,229,241,113,216, 49, 21},
			{  4,199, 35,195, 24,150,  5,154,  7, 18,128,226,235, 39,178,117},
			{  9,131, 44, 26, 27,110, 90,160, 82, 59,214,179, 41,227, 47,132},
			{ 83,209,  0,237, 32,252,177, 91,106,203,190, 57, 74, 76, 88,207},
			{208,239,170,251, 67, 77, 51,133, 69,249,  2,127, 80, 60,159,168},
			{ 81,163, 64,143,146,157, 56,245,188,182,218, 33, 16,255,243,210},
			{205, 12, 19,236, 95,151, 68, 23,196,167,126, 61,100, 93, 25,115},
			{ 96,129, 79,220, 34, 42,144,136, 70,238,184, 20,222, 94, 11,219},
			{224, 50, 58, 10, 73,  6, 36, 92,194,211,172, 98,145,149,228,121},
			{231,200, 55,109,141,213, 78,169,108, 86,244,234,101,122,174,  8},
			{186,120, 37, 46, 28,166,180,198,232,221,116, 31, 75,189,139,138},
			{112, 62,181,102, 72,  3,246, 14, 97, 53, 87,185,134,193, 29,158},
			{225,248,152, 17,105,217,142,148,155, 30,135,233,206, 85, 40,223},
			{140,161,137, 13,191,230, 66,104, 65,153, 45, 15,176, 84,187, 22}
	};
	
	private int [][] S_box_1 = 
	{
			{82, 9,  106,213, 48, 54,165, 56,191, 64,163,158,129,243,215,251},
			{124,227,57, 130,155, 47,255,135,52, 142, 67, 68,196,222,233,203},
			{84, 123,148,50, 166,194, 35, 61,238, 76,149, 11, 66,250,195, 78}, 
			{8,  46, 161,102, 40,217, 36,178,118, 91,162, 73,109,139,209, 37},
			{114,248,246,100,134,104,152, 22,212,164, 92,204, 93,101,182,146},
			{108,112, 72, 80,253,237,185,218, 94, 21, 70, 87,167,141,157,132},
			{144,216,171,  0,140,188,211, 10,247,228, 88,  5,184,179,69,   6},
			{208, 44, 30,143,202, 63, 15,  2,193,175,189,  3,  1, 19,138,107},
			{ 58,145, 17, 65, 79,103,220,234,151,242,207,206,240,180,230,115},
			{150,172,116, 34,231,173, 53,133,226,249, 55,232, 28,117,223,110},
			{ 71,241, 26,113, 29, 41,197,137,111,183, 98, 14,170, 24,190, 27},
			{252, 86, 62, 75,198,210,121, 32,154,219,192,254,120,205, 90,244},
			{ 31,221,168, 51,136,  7,199, 49,177, 18, 16, 89, 39,128,236, 95},
			{ 96, 81,127,169, 25,181, 74, 13, 45,229,122,159,147,201,156,239},
			{160,224, 59, 77,174, 42,245,176,200,235,187, 60,131, 83,153, 97},
			{ 23, 43,  4,126,186,119,214, 38,225,105, 20, 99, 85, 33, 12,125}
	};
	private int[] key = 
	{
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0
	};
	private int[] Code =
	{
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0
	};

	private int[] extend_key = new int[176];
	private int[] Rcon = 
	{
			0,1,2,4,8,16,32,64,128,27,54
	};

	
	public void KeyExpansion(){
		int [] temp = new int[4];
		
		for(int i = 0; i < 16; i ++){
			extend_key [i] = key[i];			
		}
		for(int i = 4; i < 44; i ++){
			for(int j = 0; j < 4; j ++)
				temp[j] = extend_key[(i - 1) * 4 + j];
			if( i % 4 == 0){
				RotWord(temp);
				for(int j = 0; j < 4; j ++){
					temp[j] = SubWord(temp[j]);
					if( j==0 )
						temp[j] = temp[j]^Rcon[i/4];
				}
			}
			for(int j = 0; j < 4; j++){
				extend_key[(i - 0) * 4 + j] = extend_key[(i - 4) * 4 + j]^temp[j];
				StringBuffer buf = new StringBuffer(20);
			}
		}
	}
	
	/*
	 * Routate shift left the Array
	 */
	public void RotWord (int temp[]){
		int a;
		a = temp[0];
		temp[0] = temp[1];
		temp[1] = temp[2];
		temp[2] = temp[3];
		temp[3] = a;
	}
	
	/*
	 *	replace the num recording to the S box 
	 */
	public int SubWord(int a)
	{
		int x = a / 16;
		int y = a % 16;
		return S_box[x][y];
	}
	/*
	 * replace the num recording to the S box 1
	 */
	public int SubWord_1(int a)
	{
		int x = a / 16;
		int y = a % 16;
		return S_box_1[x][y];
	}
	
	/*
	 * int type transite into Hex
	 */
	   public String encodeHex(int integer) { 
	        StringBuffer buf = new StringBuffer(2); 

	            if (((int) integer & 0xff) < 0x10) { 
	                buf.append("0"); 
	            } 
	            buf.append(Long.toString((int) integer & 0xff, 16)); 
	        return buf.toString(); 
	    }
	   
	   /*
	    *function of encode 
	    */
	   public void Encode()
	   {
		   /*
		    * initiate the code, that is the code ^ the key
		    */
		   /*
		   ////////////////////////////////////////////////////
		   //System.out.print('\n');
		   System.out.print("encode=========================\n");
		   for(int k = 0; k < 16; k ++){
			   System.out.print((encodeHex(Code[k]))+",");
		   }
		   System.out.print('\n');
		   ////////////////////////////////////////////////////
		   */
		   for(int i = 0; i < 16; i ++){
			   Code[i] = extend_key[i]^Code[i];
		   }
		   for(int time = 1; time < 11; time ++)
			   Loop(time);
	   }
	   public void Loop(int time)
	   {

		   for(int i = 0; i < 16; i ++)
		   {
			   Code[i] = SubWord(Code[i]);
		   }

		   ShiftRow();

		   if(time!=10)
			   MixColumn();
		  
		   for(int i = 0; i < 16; i ++)
		   {
			   Code[i] = Code[i]^extend_key[time*16+i];
		   }
		   /*
		   ////////////////////////////////////////////////////
		   //System.out.print('\n');
		   for(int k = 0; k < 16; k ++){
			   System.out.print((encodeHex(Code[k]))+",");
		   }
		   System.out.print('\n');
		   ////////////////////////////////////////////////////
		   */
	   }
	   public void ShiftRow()
	   {
		   int temp;
		   temp = Code[1];
		   Code[1] = Code[5];
		   Code[5] = Code[9];
		   Code[9] = Code[13];
		   Code[13] = temp;

		   temp = Code[2];
		   Code[2] = Code[10];
		   Code[10] = temp;
		   temp = Code[6];
		   Code[6] = Code[14];
		   Code[14] = temp;
	   
		   temp = Code[3];
		   Code[3] = Code[15];
		   Code[15] = Code[11];
		   Code[11] = Code[7];
		   Code[7] = temp;
	   }
	   
	   public void MixColumn()
	   {
		   int[] temp = new int[16];
		   for(int i = 0 ;i < 4 ; i ++)
		   {
			   temp[i*4] = Multi(Code[i*4],2) ^ Multi(Code[i*4+1],3) ^ 
		   			Multi(Code[i*4+2],1) ^ Multi(Code[i*4+3],1);
			   temp[i*4+1] = Multi(Code[i*4],1) ^ Multi(Code[i*4+1],2) ^ 
			   		Multi(Code[i*4+2],3) ^ Multi(Code[i*4+3],1);
			   temp[i*4+2] = Multi(Code[i*4],1) ^ Multi(Code[i*4+1],1) ^ 
	   				Multi(Code[i*4+2],2) ^ Multi(Code[i*4+3],3);
			   temp[i*4+3] = Multi(Code[i*4],3) ^ Multi(Code[i*4+1],1) ^ 
	   				Multi(Code[i*4+2],1) ^ Multi(Code[i*4+3],2);
		   }
		   for(int i = 0; i < 16; i ++)
		   {
			   Code[i] = temp[i];
		   }
	   }
	   public int Multi(int a,int b)
	   {
	           int[]  temp = new int[8];
			   int[]  flag = new int[8];
			   int c = b;
			   for(int i= 0;i < 8;i++)
			   {
			        int c1,c2;
			        c1 = c / 2;
			        c2 = c % 2;
	                if(c2 == 1)flag[i] = 1;
	                else flag[i] = 0;
	                c = c1;
	                if(i==0)temp[i] = a;
	                else{
	                    temp[i] = temp[i-1]*2;
	                    if(temp[i]>255)temp[i] = (temp[i]%256)^27;
	                    
	                }
	           }
	           a = 0;
	           for(int i = 0; i < 8; i++)
	               a = a^(temp[i]*flag[i]);
	           return a;
			   
		   }
		   
	   

	   /*
	    * function of Decode
	    */
	   public void Decode()
	   {
		   int i,j;
		   /*
		   ////////////////////////////////////////////////////
		   //System.out.print('\n');
		   System.out.print("decode============================\n");
		   for(int k = 0; k < 16; k ++){
			   System.out.print((encodeHex(Code[k]))+",");
		   }
		   System.out.print('\n');
		   ////////////////////////////////////////////////////	
		   */
		   for(i = 0,j = 160 ; i < 16; i ++,j ++){
			   Code[i] = extend_key[j]^Code[i];
			   //System.out.print(encodeHex(Code[i]));
		   }

		   for(int time = 1; time < 11; time ++)
			   Loop_1(time);
	   }
	   
	   public void Loop_1(int time)
	   {
		   ShiftRow_1();

		   
		   for(int i = 0; i < 16; i ++)
		   {
			   Code[i] = SubWord_1(Code[i]);
		   }
		   
		   for(int i = 0; i < 16; i ++)
		   {
			   Code[i] = Code[i]^extend_key[160-time*16+i];
		   }

		   if(time!=10)
			   MixColumn_1();
		   
		   /*
		   ////////////////////////////////////////////////////
		   //System.out.print('\n');
		   for(int k = 0; k < 16; k ++){
			   System.out.print((encodeHex(Code[k]))+",");
		   }
		   System.out.print('\n');
		   ////////////////////////////////////////////////////	
		   */ 
	   }
	   public void ShiftRow_1()
	   {
		   int temp;
		   temp = Code[13];
		   Code[13] = Code[9];
		   Code[9] = Code[5];
		   Code[5] = Code[1];
		   Code[1] = temp;

		   temp = Code[2];
		   Code[2] = Code[10];
		   Code[10] = temp;
		   temp = Code[6];
		   Code[6] = Code[14];
		   Code[14] = temp;
	   
		   temp = Code[3];
		   Code[3] = Code[7];
		   Code[7] = Code[11];
		   Code[11] = Code[15];
		   Code[15] = temp;
	   }
	   
	   public void MixColumn_1()
	   {
		   int[] temp = new int[16];
		   for(int i = 0 ;i < 4 ; i ++)
		   {
			   temp[i*4] = Multi(Code[i*4],14) ^ Multi(Code[i*4+1],11) ^ 
		   			Multi(Code[i*4+2],13) ^ Multi(Code[i*4+3],9);
			   temp[i*4+1] = Multi(Code[i*4],9) ^ Multi(Code[i*4+1],14) ^ 
			   		Multi(Code[i*4+2],11) ^ Multi(Code[i*4+3],13);
			   temp[i*4+2] = Multi(Code[i*4],13) ^ Multi(Code[i*4+1],9) ^ 
	   				Multi(Code[i*4+2],14) ^ Multi(Code[i*4+3],11);
			   temp[i*4+3] = Multi(Code[i*4],11) ^ Multi(Code[i*4+1],13) ^ 
	   				Multi(Code[i*4+2],9) ^ Multi(Code[i*4+3],14);
		   
		   }
		   for(int i = 0; i < 16; i ++)
		   {
			   Code[i] = temp[i];
		   }
	   }
	   private JTextArea original, keytext, to_code, to_original;
	   private JButton encode,decode;
	   //private JLabel label;
	   
	   public void getCode(int code_num[])
	   {
		   for(int i = 0; i < 16; i ++)
		   {
			   Code[i] = code_num[i];
		   }
	   }
	   
	   public void outCode(int code_num[])
	   {
		   	for(int i = 0; i < 16; i ++)
		   		code_num[i] = Code[i];
	   }
	   
	   public void getKey(int key_num[])
	   {
		   for(int i = 0; i < 16; i++)
		   {
			   key[i] = 0;
			   Code[i] = 0;
		   }
		   for(int i = 0; i < 16; i ++)
		   {
			   key[i] = key_num[i]^key[i];
		   }
	   }
	   public void actionPerformed(ActionEvent e)
	   {
		   
		   if(e.getSource() == encode)
		   {
			   /*
			    * get the keys(the keys is initialized with all 0s.)
			    * if the keys are shorter or longer than 16 bits, 
			    * it add '0' to the tail
			    * until it mod(16) equals to 0 .
			    */
			   int i,j;
			   int []code_num = new int[16];
			   int []key_num = new int[16];
			   String temp_key = keytext.getText();
			   String temp_code = original.getText();
			   int len_key = temp_key.length();
			   int len_code = temp_code.length();

			   if(len_key%16!=0)
			   {
				   for(i = len_key%16; i < 16; i ++)
				   {
					   temp_key += " ";
				   }
			   }
			   for(i = 0, j = 0; j < temp_key.length(); j ++, i ++)
			   {
				   key_num[i] = temp_key.charAt(i);
				   if(i == 15)
				   {
					   getKey(key_num);
					   i = -1;
				   }
			   }
			   
			   KeyExpansion();
			   
			   /*
			    * get the code
			    * if the keys are shorter or longer than 16 bits, 
			    * it add '0' to the tail
			    * until it mod(16) equals to 0
			    */
			   //////////////////////////////////////////
			   
			   String output = "";
			   
			   if(len_code%16!=0)
			   {
				   for(i = len_code%16; i < 16; i++)
					   temp_code += " ";
			   }
			   for(i = 0,j = 0; j < temp_code.length(); j ++, i ++)
			   {
				   
				   code_num[i] = (temp_code.charAt(j));
				   
				   if(i == 15)
				   {
					   getCode(code_num);
					   Encode();
					   outCode(code_num);
					   for(i = 0; i < 16; i ++)
					   {
						   /*
						    * change the ASCII code into character
						    */
						   //String aa = String.valueOf(char(code_num[i]));
						   output += Character.toChars(code_num[i])[0];
					   }
					   to_code.setText(output);
					   i = -1;
				   }
			   }
		   }
		   
		   /*
		    *  Button event of decode
		    */
		   if(e.getSource() == decode)
		   {
			   /*
			    * get the keys(the keys is initialized with all 0s.)
			    * if the keys are shorter or longer than 16 bits, 
			    * it add '0' to the tail
			    * until it mod(16) equals to 0 .
			    */
			   int i,j;
			   int []code_num = new int[16];
			   int []key_num = new int[16];
			   String temp_key = keytext.getText();
			   String temp_code = to_code.getText();
			   int len_key = temp_key.length();
			   int len_code = temp_code.length();
			   //char shit;
			   //System.out.print(len_code);
			   if(len_key%16!=0)
			   {
				   for(i = len_key%16; i < 16; i ++)
				   {
					   temp_key += " ";
				   }
			   }
			   for(i = 0, j = 0; j < temp_key.length(); j ++, i ++)
			   {
				   key_num[i] = temp_key.charAt(i);
				   if(i == 15)
				   {
					   getKey(key_num);
					   i = -1;
				   }
			   }
			   
			   KeyExpansion();
			   
			   /*
			    * get the code
			    * if the keys are shorter or longer than 16 bits, 
			    * it add '0' to the tail
			    * until it mod(16) equals to 0
			    */
			   //////////////////////////////////////////
			   
			   String output = "";
			   
			   if(len_code%16!=0)
			   {
				   for(i = len_code%16; i < 16; i++)
					   temp_code += " ";
			   }
			   for(i = 0,j = 0; j < temp_code.length(); j ++, i ++)
			   {
				   
				   code_num[i] = (temp_code.charAt(j));
				   if(i == 15)
				   {
					   getCode(code_num);
					   Decode();
					   outCode(code_num);
					   

					   
					   for(i = 0; i < 16; i ++)
					   {
						   output += Character.toChars(code_num[i])[0];
						   //output += String.valueOf(code_num[i]);
					   }
					   to_original.setText(output);
					   i = -1;
				   }
			   }
		   }
	   }
	/*
	 * construct function
	 */
	public AES()
	{
		Container container = getContentPane();
		container.setLayout(new GridLayout(1,1,5,5));
		//container.addMouseListener(this);
		//container.addMouseMotionListener(this);
		
		JPanel p1 = new JPanel();
		//p1.addMouseListener(this);
		p1.setLayout(new GridLayout(4,1,5,5));
		
		//p1.add(label = new JLabel());
		JPanel p1_1 = new JPanel();
		p1_1.setLayout(new BorderLayout());
		JPanel p1_2 = new JPanel();
		p1_2.setLayout(new GridLayout(2,1,5,5));
		JPanel p1_3 = new JPanel();
		p1_3.setLayout(new BorderLayout());
		JPanel p1_4 = new JPanel();
		p1_4.setLayout(new BorderLayout());
		
		//p1_1.add(new JLabel(" 明文"),BorderLayout.NORTH);
		p1_1.setBorder(BorderFactory.createTitledBorder("明文  (暂时无法加密汉字)"));
		p1_1.add(original = new JTextArea(),BorderLayout.CENTER);
		
		JPanel p1_2_2 = new JPanel();
		p1_2_2.setLayout(new BorderLayout());
		p1_2_2.setBorder(BorderFactory.createTitledBorder("密钥  (暂时无法使用汉字做密钥)"));
		//p1_2_2.add(new JLabel("密钥"),BorderLayout.NORTH);
		p1_2_2.add(keytext = new JTextArea(),BorderLayout.CENTER);
		p1_2.add(p1_2_2);
		
		JPanel p1_2_1 = new JPanel();
		p1_2_1.setLayout(new FlowLayout());
		p1_2_1.add(encode = new JButton("加密"));
		p1_2_1.add(decode = new JButton("解密"));
		p1_2.add(p1_2_1);
		
		//p1_3.add(new JLabel(" 密文"),BorderLayout.NORTH);
		p1_3.setBorder(BorderFactory.createTitledBorder("密文  (暂时无法解密汉字)"));
		p1_3.add(to_code = new JTextArea(),BorderLayout.CENTER);
		
		p1_4.setBorder(BorderFactory.createTitledBorder("原文  (比较)"));
		//p1_4.add(new JLabel(" 原文（比较）"),BorderLayout.NORTH);
		p1_4.add(to_original = new JTextArea(),BorderLayout.CENTER);
		
		p1.add(p1_1);
		p1.add(p1_2);
		p1.add(p1_3);
		p1.add(p1_4);
		
		container.add(p1);
		
		original.setLineWrap(true);
		p1_1.add(new JScrollPane(original));
		to_code.setLineWrap(true);
		p1_3.add(new JScrollPane(to_code));
		to_original.setLineWrap(true);
		p1_4.add(new JScrollPane(to_original));
		keytext.setLineWrap(true);
		p1_2_2.add(new JScrollPane(keytext));

		encode.addActionListener(this);
		decode.addActionListener(this);

		JMenuBar bar = new JMenuBar();
		//JPanel menu = new JPanel();
		//menu.setLayout(new GridLayout(1,10));

		JMenu theFile = new JMenu("文件");
		JMenu editMenu = new JMenu("编辑");
		JMenu helpMenu = new JMenu("帮助");
		
		JMenuItem firstMenu = new JMenuItem("退出");
		
		JMenuItem cutMenu = new JMenuItem("剪切");
		JMenuItem copyMenu = new JMenuItem("复制");
		JMenuItem pasteMenu = new JMenuItem("粘贴");
		
		JMenuItem secondMenu = new JMenuItem("关于");
		
		editMenu.add(cutMenu);
		editMenu.add(copyMenu);
		editMenu.add(pasteMenu);
		
		theFile.add(firstMenu);
		
		helpMenu.add(secondMenu);
		
		bar.add(theFile);
		bar.add(editMenu);
		bar.add(helpMenu);
		
		setJMenuBar(bar);
		setSize(300,300);
		
		cutMenu.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				original.cut();
				keytext.cut();
				to_code.cut();
				to_original.cut();
			}
		});
		
		copyMenu.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				original.copy();
				keytext.copy();
				to_code.copy();
				to_original.copy();
			}
		});
		
		pasteMenu.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				//if(original.getCursor() == DEFAULT_CURSOR)
					original.paste();
				System.out.print(original.getCursor());
				keytext.paste();
				to_code.paste();
				to_original.paste();
			}
		});
		
		firstMenu.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		
		secondMenu.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JFrame newF = new JFrame("关于");
				JPanel p1 = new JPanel();
				
				p1.setLayout(new GridLayout(5,1,10,5));
				p1.add(new JLabel("版本AES 1.0"));
				p1.add(new JLabel("创建于 2006/6/3"));
				p1.add(new JLabel("创建者：计算机B班  李德苑  033511063"));
				p1.add(new JLabel("E-mail：ldyhot@163.com"));
				p1.add(new JLabel("说明：暂时无法加密与解密汉字"));

				newF.add(p1);
				newF.setSize(400,200);
				
				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				int screenWidth = screenSize.width;
				int screenHeight = screenSize.height;
				
				Dimension frameSize = newF.getSize();
				int x = (screenWidth - frameSize.width)/2;
				int y = (screenHeight - frameSize.height)/2;
				newF.setLocation(x,y);
				//newF.setVisible(true);
				newF.setVisible(true);
			}
		});
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AES test = new AES();
		test.setTitle("AES");
		test.setSize(600,600);
		test.setVisible(true);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		
		Dimension frameSize = test.getSize();
		int x = (screenWidth - frameSize.width)/2;
		int y = (screenHeight - frameSize.height)/2;
		
		test.setLocation(x,y);
		
		test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//System.out.println("Welcome to java");
	} 
}
