package univ.smu.w9.colink.guiComponent;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * 기본 트리 노드
 * @author sukhwan
 *
 */
public class MyTreeNode extends DefaultMutableTreeNode {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4342789708280541543L;
	
	private String realPath;

	public String getRealPath() {
		return realPath;
	}

	public void setRealPath(String realPath) {
		this.realPath = realPath;
	}
	
	
	
}
