package edu.buffalo.cse.irf14.analysis;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class NumberFilter extends TokenFilter {

	
	TokenStream t_stream;
	String numberRegex="([\\.]*[-]*(\\d)+[\\.]*[,]*[-]*(\\d)*)";
	Pattern numberRegexPattern= Pattern.compile(numberRegex);
	Matcher numberMatcher;
	public NumberFilter(TokenStream stream) {
		// TODO Auto-generated constructor stub
		super(stream);
		t_stream=stream;
		f_type=TokenFilterType.NUMERIC;
	}
	int len=0;
	@Override
	public boolean increment() throws TokenizerException {
		// TODO Auto-generated method stub
//		Removes numbers of this format
//		1231782637
//		123563.12763
//		123/23==>/
//		.234
//		skfdjks7==>skfdjks
		Token current_token;
		
			
			current_token=t_stream.next();
			if(current_token==null)
				return false;
			String str=current_token.toString();
			numberMatcher= numberRegexPattern.matcher(str);
			
			String[] strArr= str.split("\\d");
			if(strArr.length>1){
				str="";
				for(String temp: strArr){
					str+=temp;
				
				}
				
				str=str.replaceAll("\\.|-|,", "");
				if("".equals(str))
					t_stream.remove();
				else
                {
                    current_token.setTermText(str);
                    t_stream.replace(current_token);
                }
			
			}else if(numberMatcher.matches() ){
				len=str.length();
				if(len==8 && Integer.valueOf(str)>21001231){
					t_stream.remove();
				}
				else if(len!=8)
				{
				str=str.replaceAll(numberRegex, "");
				
				if("".equals(str))
					t_stream.remove();
                else{
                    current_token.setTermText(str);
                    t_stream.replace(current_token);
                }
				}
			}
			if (t_stream.hasNext()) {
			return true;
			
		}
		
		return false;
	}
	
	@Override
	public TokenStream getStream() {
		// TODO Auto-generated method stub
		return t_stream;
	}
}
