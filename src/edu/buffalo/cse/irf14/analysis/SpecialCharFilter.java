package edu.buffalo.cse.irf14.analysis;

import java.text.Normalizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpecialCharFilter  extends TokenFilter {
	// Update on 17th Sep by anand
	
	TokenStream t_stream;
	public SpecialCharFilter(TokenStream stream) {
		super(stream);
		// TODO Auto-generated constructor stub
		t_stream=stream;
	}
	String intregex = "(.)*(\\d)(.)*";      
    Pattern intpattern = Pattern.compile(intregex);
	@Override
	public boolean increment() throws TokenizerException {
		// TODO Auto-generated method stub
		
		 char[] punctuations={'.',',','?','!','\''};
		 String ps=new String(punctuations);
		 StringBuffer res = new StringBuffer();
		 int flag=0;
			Token current_token=t_stream.next();
			String str=current_token.getTermText();
			if(current_token==null)
				return false;
			char [] buf=current_token.getTermBuffer();
			
			for(char a:buf)
			{
				if(Character.isLetterOrDigit(a) || ps.indexOf(a)>=0)
					res.append(a);
				else if(a=='-')
				{
					 Matcher matcher = intpattern.matcher(str);
			         boolean isMatched = matcher.matches();
			        
			        if(isMatched)
			        	 res=res.append(a);
					
				}
			}
			if(flag==1)
			t_stream.add_next(new Token(res.toString()));
			else
				t_stream.replace(new Token(res.toString()));
			
			if(t_stream.hasNext())
				return true;
			else
				return false;
			
	}

	@Override
	public TokenStream getStream() {
		// TODO Auto-generated method stub
		return t_stream;
	}

}