package com.pharoouzy.dataprocessing;

import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private static DataManager ourInstance = null;

    private List<CourseInfo> mCourses = new ArrayList<>();
    private List<NoteInfo> mNotes = new ArrayList<>();

    public static DataManager getInstance() {
        if(ourInstance == null) {
            ourInstance = new DataManager();
            ourInstance.initializeCourses();
            ourInstance.initializeExampleNotes();
        }
        return ourInstance;
    }

    public String getCurrentUserName() {
        return "Umar-Farouq";
    }

    public String getCurrentUserEmail() {
        return "pharoouzy@gmail.com";
    }

    public List<NoteInfo> getNotes() {
        return mNotes;
    }

    public int createNewNote() {
        NoteInfo note = new NoteInfo(null, null, null);
        mNotes.add(note);
        return mNotes.size() - 1;
    }

    public int findNote(NoteInfo note) {
        for(int index = 0; index < mNotes.size(); index++) {
            if(note.equals(mNotes.get(index)))
                return index;
        }

        return -1;
    }

    public void removeNote(int index) {
        mNotes.remove(index);
    }

    public List<CourseInfo> getCourses() {
        return mCourses;
    }

    public CourseInfo getCourse(String id) {
        for (CourseInfo course : mCourses) {
            if (id.equals(course.getCourseId()))
                return course;
        }
        return null;
    }

    public List<NoteInfo> getNotes(CourseInfo course) {
        ArrayList<NoteInfo> notes = new ArrayList<>();
        for(NoteInfo note:mNotes) {
            if(course.equals(note.getCourse()))
                notes.add(note);
        }
        return notes;
    }

    public int getNoteCount(CourseInfo course) {
        int count = 0;
        for(NoteInfo note:mNotes) {
            if(course.equals(note.getCourse()))
                count++;
        }
        return count;
    }

    private DataManager() {
    }

    //region Initialization code

    private void initializeCourses() {
        mCourses.add(initializeCourse1());
        mCourses.add(initializeCourse2());
        mCourses.add(initializeCourse3());
        mCourses.add(initializeCourse4());
    }

    public void initializeExampleNotes() {
        final DataManager dm = getInstance();

        CourseInfo course = dm.getCourse("data_management");
        course.getModule("data_management_m01").setComplete(true);
        course.getModule("data_management_m02").setComplete(true);
        course.getModule("data_management_m03").setComplete(true);
        mNotes.add(new NoteInfo(course, "Sample Title 1",
                "This is a Sample !"));
        mNotes.add(new NoteInfo(course, "Sample Title 2",
                "This is a Sample !"));

        course = dm.getCourse("information");
        course.getModule("information_m01").setComplete(true);
        course.getModule("information_m02").setComplete(true);
        mNotes.add(new NoteInfo(course, "Sample Title 3",
                "This is a Sample !"));
        mNotes.add(new NoteInfo(course, "Sample Title 4",
                "This is a sample !"));

        course = dm.getCourse("tools_for_processing");
        course.getModule("tools_for_processing_m01").setComplete(true);
        course.getModule("tools_for_processing_m02").setComplete(true);
        course.getModule("tools_for_processing_m03").setComplete(true);
        course.getModule("tools_for_processing_m04").setComplete(true);
        mNotes.add(new NoteInfo(course, "Sample Title 5",
                "This is a Sample !"));
        mNotes.add(new NoteInfo(course, "Sample Title 6",
                "This is a Sample !"));

        course = dm.getCourse("computer_maintenance");
        course.getModule("computer_maintenance_m01").setComplete(true);
        course.getModule("computer_maintenance_m02").setComplete(true);
        mNotes.add(new NoteInfo(course, "Sample Title 7",
                "This is a Sample !"));
        mNotes.add(new NoteInfo(course, "Sample Title 8",
                "This is a Sample !"));
    }

    private CourseInfo initializeCourse1() {
        List<ModuleInfo> modules = new ArrayList<>();
        modules.add(new ModuleInfo("data_management_m01", "Introduction"));
        modules.add(new ModuleInfo("data_management_m02", "Types of Data Models"));
        modules.add(new ModuleInfo("data_management_m03", "Data Modelling"));
        modules.add(new ModuleInfo("data_management_m04", "Normal Forms"));
        modules.add(new ModuleInfo("data_management_m05", "Entry Relational Models"));
        modules.add(new ModuleInfo("data_management_m06", "Relational Models"));
        modules.add(new ModuleInfo("data_management_m07", "File Organization"));

        return new CourseInfo("data_management", "Data Management", modules);
    }

    private CourseInfo initializeCourse2() {
        List<ModuleInfo> modules = new ArrayList<>();
        modules.add(new ModuleInfo("information_m01", "Transformation"));
        modules.add(new ModuleInfo("information_m02", "The Internet"));

        return new CourseInfo("information", "Information", modules);
    }

    private CourseInfo initializeCourse3() {
        List<ModuleInfo> modules = new ArrayList<>();
        modules.add(new ModuleInfo("tools_for_processing_m01", "Information"));
        modules.add(new ModuleInfo("tools_for_processing_m02", "Presentation Packages"));
        modules.add(new ModuleInfo("tools_for_processing_m03", "Web Design Packages"));
        modules.add(new ModuleInfo("tools_for_processing_m04", "Graphics Packages"));

        return new CourseInfo("tools_for_processing", "Tools For Processing", modules);
    }

    private CourseInfo initializeCourse4() {
        List<ModuleInfo> modules = new ArrayList<>();
        modules.add(new ModuleInfo("computer_maintenance_m01", "Ethics and Issues"));
        modules.add(new ModuleInfo("computer_maintenance_m02", "Maintenance of Computer"));

        return new CourseInfo("computer_maintenance", "Computer Maintenance", modules);
    }
    //endregion

}
