package quests;

import java.awt.Container;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class TaskListing {
	
	public static ArrayList<String> taskListData = new ArrayList<String>();
	public static DefaultListModel<String> taskListModel = new DefaultListModel<String>();
	public static ArrayList<String> taskRemove = new ArrayList<String>();
	public static boolean runOneTime = false;
	
	public static void updateTasks(){
		taskListModel.removeAllElements();
		for(int index = 0; index < taskListData.size();index ++){
			if(!taskListModel.contains(taskListData.get(index))){
				taskListModel.addElement(taskListData.get(index));
			}
		}
		
	}
	public static void removeTasks(ArrayList<String> taskRemove) {
		if (taskListData.contains(taskRemove.get(taskRemove.size()-1))) {
			for(String taskToRemove: taskRemove){
				if(taskListData.contains(taskToRemove)){
					System.out.println("Removing task: " + taskToRemove);
					taskListData.remove(taskToRemove);
					updateTasks();
				}
			}
		}else System.out.println("Nothing to remove: " + taskRemove.get(taskRemove.size()-1).toString());
	}
	public static void updateTaskRemove(String... tasks) {
		for(String t: tasks){
			if(!TaskListing.taskRemove.contains(t)){
				TaskListing.taskRemove.add(t);
			}
		}
	}
	static class TaskListForm extends JFrame {
		public TaskListForm() {
			taskListModel.addElement("");
			initComponents();
			taskList.setModel(taskListModel);
		}

		private void taskListPropertyChange(PropertyChangeEvent e) {
			if(DeltaQuester.e)
				this.dispose();
			
			taskList.removeAll();
			taskList.setModel(taskListModel);
		}

		private void initComponents() {
			scrollPane1 = new JScrollPane();
			taskList = new JList();

			//======== this ========
			setTitle("Questing status");
			setResizable(false);
			Container contentPane = getContentPane();

			//======== scrollPane1 ========
			{

				//---- taskList ----
				taskList.addPropertyChangeListener(new PropertyChangeListener() {
					@Override
					public void propertyChange(PropertyChangeEvent e) {
						taskListPropertyChange(e);
					}
				});
				scrollPane1.setViewportView(taskList);
			}

			GroupLayout contentPaneLayout = new GroupLayout(contentPane);
			contentPane.setLayout(contentPaneLayout);
			contentPaneLayout.setHorizontalGroup(
				contentPaneLayout.createParallelGroup()
					.addGroup(contentPaneLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
						.addContainerGap())
			);
			contentPaneLayout.setVerticalGroup(
				contentPaneLayout.createParallelGroup()
					.addGroup(contentPaneLayout.createSequentialGroup()
						.addGap(29, 29, 29)
						.addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
						.addContainerGap())
			);
			pack();
			setLocationRelativeTo(getOwner());
			// JFormDesigner - End of component initialization  //GEN-END:initComponents
		}

		// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
		// Generated using JFormDesigner Evaluation license - Christian Day
		private JScrollPane scrollPane1;
		private JList taskList;
		// JFormDesigner - End of variables declaration  //GEN-END:variables
	}

	
}
