package com.bianki.biankiapp.ASK;


import com.bianki.biankiapp.ClassModel.getcurrentprofile;
import com.bianki.biankiapp.ClassModel.puplicQuestion;

import java.util.List;

public interface HomeView {
    void showLoading();
    void hideLoading();
    void onErrorLoading(String message);
    void Result ( Boolean message);
    void puplicQuestion (List<puplicQuestion.Question> questions) ;
    void Resultofreplay (String res) ;
    void getcurrentprofile(getcurrentprofile.User users );
    void deleteQuestion (String deleteQuestion) ;


}
