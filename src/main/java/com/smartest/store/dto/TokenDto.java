package com.smartest.store.dto;

import java.util.HashMap;
import java.util.Map;

public class TokenDto {
	
	public enum Type 
	{
		BEARER("Bearer"), 
	    BASIC("Basic"), 
	    DIGEST("Digest");
	 
	    private String typeName;
	 
	    Type(String typeName) {
	        this.typeName = typeName;
	    }
	 
		public String getTypeName() {
			return typeName;
		}

		private static final Map<String, Type> lookup = new HashMap<>();
		static {
			for (Type env : Type.values()) {
				lookup.put(env.getTypeName(), env);
			}
		}

		public static Type get(String url) {
			return lookup.get(url);
		}
	}

	private String token;
	private String type;

	public TokenDto(String token, Type type) {
		this.token = token;
		this.type = type.getTypeName();
	}

	public String getToken() {
		return token;
	}

	public String getType() {
		return type;
	}

}
