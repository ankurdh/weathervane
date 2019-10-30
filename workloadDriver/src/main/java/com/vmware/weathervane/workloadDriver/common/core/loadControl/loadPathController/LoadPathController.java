package com.vmware.weathervane.workloadDriver.common.core.loadControl.loadPathController;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;

@JsonTypeInfo(use = com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME, include = As.PROPERTY, property = "type")
@JsonSubTypes({ 
	@Type(value = AllMustPassLoadPathController.class, name = "allpass"), 
})
public interface LoadPathController {
	/**
	 * Called by a loadPath to register its result in the interval identified by 
	 * interval name. 
	 *  
	 * @param loadPathName The name of the loadPath whose result is being registered
	 * @param intervalName The name of the interval to which this result belongs
	 * @param passed True if the interval passed, false otherwise
	 */
	void postIntervalResult(String loadPathName, String intervalName, boolean passed);
	
	void registerIntervalResultCallback(String name, LoadPathIntervalResultWatcher watcher);
}
