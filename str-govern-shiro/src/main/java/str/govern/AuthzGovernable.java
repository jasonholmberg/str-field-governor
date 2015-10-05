package str.govern;

import java.lang.reflect.Method;

import str.govern.governors.Governor;

public interface AuthzGovernable extends Governable {

	@Override
	default Governor findGovernor(String currentMethod, Method m) {
		return Governable.super.findGovernor(currentMethod, m);
	}
}
