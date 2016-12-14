package cn.edu.gdmec.w07150837.contacts;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Created by weiruibo on 12/12/16.
 */

public class ContactMessageActivity extends AppCompatActivity {

    private TextView nameEditText;
    private TextView mobileEditText;
    private TextView qqEditText;
    private TextView companyEditText;
    private TextView addressEditText;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_message);
        setTitle("联系人信息");
        nameEditText = (TextView) this.findViewById(R.id.name);
        mobileEditText = (TextView) this.findViewById(R.id.mobile);
        qqEditText = (TextView) this.findViewById(R.id.qq);
        companyEditText = (TextView) this.findViewById(R.id.company);
        addressEditText = (TextView) this.findViewById(R.id.address);

        Bundle localBundle = getIntent().getExtras();
        int id = localBundle.getInt("user_ID");

        ContactsTable ct = new ContactsTable(this);
        user = ct.getUserById(id);

        nameEditText.setText("姓名:" + user.getName());
        mobileEditText.setText("电话:" + user.getMobile());
        qqEditText.setText("qq:" + user.getQq());
        companyEditText.setText("单位:" + user.getCompany());
        addressEditText.setText("地址:" + user.getAddress());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, 1, Menu.NONE, "返回");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
