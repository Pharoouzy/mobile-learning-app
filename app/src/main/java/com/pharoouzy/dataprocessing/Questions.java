package com.pharoouzy.dataprocessing;

public class Questions {

    public  String questions [] = {
            "What is the use of All Caps feature in MS-Word ?",
            "By default, your documents print in ___________ mode.",
            "Which of the following is a correct format of Email address?",
            "______ controls the way in which the computer system functions and provides a means by which users can interact with the computer.",
            "All of the following are examples of real security and privacy risk EXCEPT _______ .",
            "On an Excel spreadsheet the active cell is indicated by _______ ?",
            "To auto fit the width of a column",
            "_________ is a way to quickly access a favorite website by saving it in your browser.",
            "The term 'Pentium' is related to ______ .",
            "What does HTTP stands for?"
    };

    private  String options [][] = {
            {"It shows all the image captions", "It adds captions for selected Image", "It changes all selected text into Capital Letter", "None of the above"},
            {"Landscape", "Page setup", "Portrait", "Print preview"},
            {"umar@website.com", "umar@website,com", "umar(a)website.com", "umar.website.com"},
            {"Central Processing Unit", "Operating System", "Monitor", "Mouse"},
            {"Hackers", "Spam", "Identity theft", "Viruses"},
            {"A dotted border", "By italic text", "A blinking border", "A dark wide border"},
            {"Double click the right border of the column", "Double click the left border of the column", "Double click the column header", "None of the above"},
            {"Cookie", "Blog", "Bookmark", "Both A & C"},
            {"DVD", "CPU", "Hard Disk", "Microprocessor"},
            {"Head Tail Transfer Protocol", "HyperText Transfer Protocol", "HyperTest Transfer Protocol", "HyperText Transfers Protocol"}
    };

    private String answers [] = {
            "It changes all selected text into Capital Letter",
            "Portrait",
            "umar@website.com",
            "Operating System",
            "Spam",
            "A dark wide border",
            "Double click the right border of the column",
            "Bookmark",
            "Microprocessor",
            "HyperText Transfer Protocol"
    };

    public String getQuestion(int i){
        String question = questions[i];

        return question;
    }

    public int getTotal(){
        return questions.length;
    }

    public String getOption1(int i){
        String option = options[i][0];

        return option;
    }

    public String getOption2(int i){
        String option = options[i][1];

        return option;
    }

    public String getOption3(int i){
        String option = options[i][2];

        return option;
    }

    public String getOption4(int i){
        String option = options[i][3];

        return option;
    }

    public String getAnswer(int i){
        String answer = answers[i];

        return answer;
    }
}
