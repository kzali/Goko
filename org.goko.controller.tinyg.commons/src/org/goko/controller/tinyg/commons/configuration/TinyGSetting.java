/*
 *
 *   Goko
 *   Copyright (C) 2013  PsyKo
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package org.goko.controller.tinyg.commons.configuration;


/**
 * A generic TinyG setting
 * @author PsyKo
 *
 */
public abstract class TinyGSetting<T> {
	/** The string identifier of the setting */
	private String identifier;
	/** The value of the setting */
	private T value;
	/** Read only mode */
	private boolean readOnly;
	/** Assigned boolean, indicating that this value was actually assigned from a set statement*/
	private boolean assigned;
	
	/**
	 * @param identifier  string identifier of the setting
	 * @param value value of the setting
	 * @param readonly is the parameter read only
	 */
	public TinyGSetting(String identifier, T value, boolean readonly) {
		this(identifier, value, readonly, false);
	}
	
	/**
	 * @param identifier  string identifier of the setting
	 * @param value value of the setting
	 * @param readonly the parameter read only
	 * @param assigned the assigned value
	 */
	protected TinyGSetting(String identifier, T value, boolean readonly, boolean assigned) {
		super();
		this.identifier = identifier;
		this.value = value;
		this.readOnly = readonly;
		this.assigned = assigned;
	}

	/**
	 * @param identifier  string identifier of the setting
	 * @param value value of the setting
	 */
	public TinyGSetting(String identifier, T value) {
		this(identifier, value, false);
	}

	/**
	 * @return the identifier
	 */
	public String getIdentifier() {
		return identifier;
	}

	/**
	 * @param identifier the identifier to set
	 */
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	/**
	 * @return the value
	 */
	public T getValue() {
		return clone(value);
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(T value) {
		this.value = clone(value);
		this.setAssigned(true);
	}

	@SuppressWarnings("rawtypes")
	public Class getType(){
		return value.getClass();
	}

	/**
	 * @return the readOnly
	 */
	public boolean isReadOnly() {
		return readOnly;
	}

	/**
	 * @param readOnly the readOnly to set
	 */
	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

	protected abstract T clone(T value);

	/**
	 * @return the assigned
	 */
	public boolean isAssigned() {
		return assigned;
	}

	/**
	 * @param assigned the assigned to set
	 */
	public void setAssigned(boolean assigned) {
		this.assigned = assigned;
	}
	
	protected abstract TinyGSetting<T> copy();
}
