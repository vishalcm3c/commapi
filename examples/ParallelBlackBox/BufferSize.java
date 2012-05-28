/*
 * @(#)BufferSize.java	1.5 00/05/04 SMI
 * 
 * Author: Tom Corson
 * 
 * Copyright 2003 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * 
 * Sun grants you ("Licensee") a non-exclusive, royalty free, license
 * to use, modify and redistribute this software in source and binary
 * code form, provided that i) this copyright notice and license appear
 * on all copies of the software; and ii) Licensee does not utilize the
 * software in a manner which is disparaging to Sun.
 * 
 * This software is provided "AS IS," without a warranty of any kind.
 * ALL EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES,
 * INCLUDING ANY IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE OR NON-INFRINGEMENT, ARE HEREBY EXCLUDED. SUN AND
 * ITS LICENSORS SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY
 * LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THE
 * SOFTWARE OR ITS DERIVATIVES. IN NO EVENT WILL SUN OR ITS LICENSORS
 * BE LIABLE FOR ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT,
 * INDIRECT, SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES,
 * HOWEVER CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY, ARISING
 * OUT OF THE USE OF OR INABILITY TO USE SOFTWARE, EVEN IF SUN HAS BEEN
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.
 * 
 * This software is not designed or intended for use in on-line control
 * of aircraft, air traffic, aircraft navigation or aircraft
 * communications; or in the design, construction, operation or
 * maintenance of any nuclear facility. Licensee represents and
 * warrants that it will not use or redistribute the Software for such
 * purposes.
 */
import java.awt.Panel;
import java.awt.Label;
import java.awt.TextField;
import java.awt.BorderLayout;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.comm.ParallelPort;

/**
 * Class declaration
 *
 *
 * @author
 * @version 1.5, 05/04/00
 */
public class BufferSize extends Panel implements MouseListener, 
	ActionListener {
    private int		 value, defaultValue;
    private Label	 label;
    private TextField    data;
    private ParallelPort port = null;
    private boolean      inputBuffer;

    /**
     * Constructor declaration
     *
     *
     * @param size
     * @param port
     * @param inputBuffer
     *
     * @see
     */
    public BufferSize(int size, ParallelPort port, boolean inputBuffer) {
	super();

	this.setPort(port);

	this.inputBuffer = inputBuffer;

	this.setLayout(new BorderLayout());

	this.label = new Label("Buffer Size");

	this.label.addMouseListener(this);
	this.add("West", this.label);

	this.data = new TextField(new Integer(defaultValue).toString(), size);

	this.data.addActionListener(this);
	this.add("East", this.data);
	this.showValue();

	this.defaultValue = this.value;
    }

    /**
     * Method declaration
     *
     *
     * @param port
     *
     * @see
     */
    public void setPort(ParallelPort port) {
	this.port = port;
    } 

    /**
     * Method declaration
     *
     *
     * @return
     *
     * @see
     */
    public int getValue() {
	if (this.port != null) {

	    /*
	     * Get the buffer size.
	     */
	    if (inputBuffer) {
		this.value = port.getInputBufferSize();
	    } else {
		this.value = port.getOutputBufferSize();
	    } 

	    return this.value;
	} else {
	    return (0);
	} 
    } 

    /**
     * Method declaration
     *
     *
     * @see
     */
    public void showValue() {
	this.data.setText(new Integer(this.getValue()).toString());
    } 

    /**
     * Method declaration
     *
     *
     * @param val
     *
     * @see
     */
    public void setValue(int val) {
	if (this.port != null) {

	    /*
	     * Set the new buffer size.
	     */
	    if (inputBuffer) {
		port.setInputBufferSize(val);
	    } else {
		port.setOutputBufferSize(val);
	    } 
	} 

	this.showValue();
    } 

    /**
     * Method declaration
     *
     *
     * @param val
     *
     * @see
     */
    public void setDefaultValue(int val) {
	this.defaultValue = val;
    } 

    /**
     * Method declaration
     *
     *
     * @param e
     *
     * @see
     */
    public void actionPerformed(ActionEvent e) {
	String s = e.getActionCommand();

	try {
	    Integer newValue = new Integer(s);

	    this.setValue(newValue.intValue());
	} catch (NumberFormatException ex) {
	    System.out.println("Bad value = " + e.getActionCommand());
	    this.showValue();
	} 
    } 

    /**
     * Method declaration
     *
     *
     * @param e
     *
     * @see
     */
    public void mouseClicked(MouseEvent e) {}

    /**
     * Method declaration
     *
     *
     * @param e
     *
     * @see
     */
    public void mouseEntered(MouseEvent e) {}

    /**
     * Method declaration
     *
     *
     * @param e
     *
     * @see
     */
    public void mouseExited(MouseEvent e) {}

    /**
     * Method declaration
     *
     *
     * @param e
     *
     * @see
     */
    public void mousePressed(MouseEvent e) {
	this.setValue(this.defaultValue);
    } 

    /**
     * Method declaration
     *
     *
     * @param e
     *
     * @see
     */
    public void mouseReleased(MouseEvent e) {}

}




