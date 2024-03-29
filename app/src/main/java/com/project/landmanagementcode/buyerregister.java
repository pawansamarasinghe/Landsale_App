package com.project.landmanagementcode;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.landsalesapp.R;

public class buyerregister extends AppCompatActivity {
	
	EditText buyname, buyemail, buypassword, buyconfirmpassword, buyphonenumber;
	Button btnbuyreg, btnbuylogin;
	buyerDBhelper buyDB;

	AwesomeValidation awesomeValidation;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.buyerregister);
		
		buyname = findViewById(R.id.buyname);
		buyemail = findViewById(R.id.buyemail);
		buypassword = findViewById(R.id.buypassword);
		buyconfirmpassword = findViewById(R.id.buyconfirmpassword);
		buyphonenumber = findViewById(R.id.buyphonenumber);
		
		btnbuyreg = findViewById(R.id.btnbuyreg);
		btnbuylogin = findViewById(R.id.btnbuylogin);
		
		buyDB = new buyerDBhelper(this);

//		//initalize validation style
//		awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
//
//		//add validation for names
//		awesomeValidation.addValidation(this,R.id.buyname, RegexTemplate.NOT_EMPTY,R.string.invalid_name);
//
//		//for mobile number
//		awesomeValidation.addValidation(this,R.id.buyphonenumber,"[0-9]{1}[0-9]{9}$",R.string.invalid_mobile);
//
//		//for email
//		awesomeValidation.addValidation(this,R.id.buyemail, Patterns.EMAIL_ADDRESS,R.string.invalid_email);
//
//		//for password
//		awesomeValidation.addValidation(this,R.id.buypassword, ".{6}",R.string.invalid_password);
//
//		//for confirm password
//		awesomeValidation.addValidation(this,R.id.buyconfirmpassword,R.id.buypassword,R.string.invalid_confirm_password);
//
		btnbuyreg.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String user = buyname.getText().toString();
				String email = buyemail.getText().toString();
				String pass = buypassword.getText().toString();
				String repass = buyconfirmpassword.getText().toString();
				String phonenumber = buyphonenumber.getText().toString();

				//check validation
//				if(awesomeValidation.validate()){
//					//on success
//					Toast.makeText(getApplicationContext()
//					,"Form Validate Succefully..",Toast.LENGTH_SHORT).show();
//					Intent intent = new Intent(getApplicationContext(), buyerlogin.class);
//					startActivity(intent);
//				}else{
//					Toast.makeText(getApplicationContext()
//					,"Validation Faild",Toast.LENGTH_SHORT).show();
//
//				}
				
				if (user.equals("") || email.equals("") || pass.equals("") || repass.equals("") || phonenumber.equals("")) {
					Toast.makeText(buyerregister.this, "Fill All The Fields", Toast.LENGTH_SHORT).show();
				} else {
					if (pass.equals(repass)) {
						Boolean usercheckResult = buyDB.checkbuyemail(email);
						if (usercheckResult == false) {
							Boolean regResult = buyDB.insertData(user, email, pass, phonenumber);
							if (regResult == true) {
								Toast.makeText(buyerregister.this, "Reggistration Successful.", Toast.LENGTH_SHORT).show();
								Intent intent = new Intent(getApplicationContext(), buyerlogin.class);
								startActivity(intent);
							} else {
								Toast.makeText(buyerregister.this, "Registration Failed.", Toast.LENGTH_SHORT).show();
							}
						} else {
							Toast.makeText(buyerregister.this, "User already Exists.\n Please Sign In", Toast.LENGTH_SHORT).show();
						}
					} else {
						Toast.makeText(buyerregister.this, "Password Not Matching", Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
		
		btnbuylogin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), buyerlogin.class);
				startActivity(intent);
			}
		});
		
		
	}
}