package shabalala.thamsanqa.tv_channel;

/**
 * Created by THAMMY on 4/4/2017.
 */

public class Config {


    //Address of our scripts of the CRUD
    public static final String URL_ADD = "http://thammy202.comli.com/addEmp.php";
    public static final String URL_GET_ALL = "http://thammy202.comli.com/getAllEmpTv.php";
    public static final String URL_LOG_IN = "http://thammy202.comli.com/LogIn.php";
    public static final String URL_GET_EMP = "http://thammy202.comli.com/getEmp.php?id=";
    public static final String URL_UPDATE_EMP = "http://thammy202.comli.com/updateEmp.php";
    public static final String URL_DELETE_EMP = "http://thammy202.comli.com/deleteEmp.php?id=";


    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_ID = "show_id";
    public static final String TAG_NAME = "description";
    public static final String TAG_DESG = "date";
    public static final String TAG_SAL = "time";
    public static final String TAG_TITLE = "title";

    //JSON Tags for logging in.
    public static final String TAG_USERNAME = "username";
    public static final String TAG_PASSWORD = "password";

    //employee id to pass with intent
    public static final String EMP_ID = "emp_id";

}
