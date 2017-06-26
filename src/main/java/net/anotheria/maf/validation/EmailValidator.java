package net.anotheria.maf.validation;

import java.util.regex.Pattern;

import net.anotheria.util.StringUtils;

/**
 * Validator class for validating email addresses. Allows empty values. 
 * If this is not allowed, combine it with <b>@ValidateNotEmpty</b> annotation. 
 * Usage:<p>@ValidateCustom(class="net.anotheria.maf.validation.EmailValidator" key="" message="")
 * private String email;</p>
 * 
 * @author dzhmud
 */
public class EmailValidator implements Validator<String> {

	private static final Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	
	@Override
	public boolean validate(String field) {
		return StringUtils.isEmpty(field) || pattern.matcher(field).matches();
	}

}
