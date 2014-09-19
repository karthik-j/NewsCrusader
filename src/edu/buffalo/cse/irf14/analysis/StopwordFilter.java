package edu.buffalo.cse.irf14.analysis;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class StopwordFilter extends TokenFilter {
	
	TokenStream t_stream;
	final static Set<String> stopwordList= new HashSet<String>(Arrays.asList("a","able","about","across","after","all","almost","also","am","among","an","and","any","are","as","at","be","because","been","but","by","can","cannot","could","dear","did","do","does","either","else","ever","every","for","from","get","got","had","has","have","he","her","hers","him","his","how","however","i","if","in","into","is","it","its","just","least","let","like","likely","may","me","might","most","must","my","neither","no","nor","not","of","off","often","on","only","or","other","our","own","rather","said","say","says","she","should","since","so","some","than","that","the","their","them","then","there","these","they","this","tis","to","too","twas","us","wants","was","we","were","what","when","where","which","while","who","whom","why","will","with","would","yet","you","your"));

	public StopwordFilter(TokenStream stream) {
		// TODO Auto-generated constructor stub
		super(stream);
		t_stream=stream;
	}

	public boolean increment() throws TokenizerException {
		Token current_token;
		
		if(t_stream.hasNext())
		{
			t_stream.next();
			current_token= t_stream.getCurrent();
			if(null==current_token)
				return false;
			if(stopwordList.contains(current_token.toString()))
				t_stream.remove();
			return true;
		}
		else
			return false;
	}
	
	public TokenStream getStream() {
		return t_stream;
	}
	
}
