package com.nullcognition.butterknifeexample;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.InjectViews;
import butterknife.OnClick;
import butterknife.OnTextChanged;

// be sure to add proguard exceptions for the generated code

public class ActivityMain extends ActionBarActivity {

   @InjectView(R.id.textView01) // singular
	 TextView textView;

   @InjectView(R.id.button01)
   Button button;



   @InjectViews({R.id.editText, R.id.editText2})
   List<EditText> editTexts; // example of mutiple grouping

   static final ButterKnife.Action<View>          DISABLE = new ButterKnife.Action<View>() {

	  @Override
	  public void apply(View view, int index){
		 view.setEnabled(false);
	  }
   };
   static final ButterKnife.Setter<View, Boolean> ENABLED = new ButterKnife.Setter<View, Boolean>() {

	  @Override
	  public void set(View view, Boolean value, int index){
		 view.setEnabled(value);
	  }
   };

   @Override
   protected void onCreate(Bundle savedInstanceState){
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.activity_main);

	  injectViews();
	  toggleViews();

   }

   private void toggleViews(){

	  ButterKnife.apply(editTexts, ENABLED, true);

	  // may also use ButterKnife.apply(nameViews, View.ALPHA, 0); // Property

	  for(EditText _e : editTexts){
		 _e.setText("Set");
	  }
   }

   private void injectViews(){

	  ButterKnife.inject(this);

   }

   @OnClick(R.id.button01)
   public void myClick(Button inButton){ // optional parameters, ex inView is not needed, but if a Specific view type is set, it will be cast automatically

	  inButton.setText("haha");

	  for(EditText _e : editTexts){
		 _e.setText("Clicked");
	  }

	  ButterKnife.apply(editTexts, DISABLE);

   }


   //  Each annotation has a default callback that it binds to. Specify the multi method using the callback parameter.
   @OnTextChanged(R.id.button01)
   void onTextChanged(CharSequence text){
	  Toast.makeText(this, "Text changed: " + text, Toast.LENGTH_SHORT)
		   .show();
   }

   @OnTextChanged(value = R.id.button01, callback = OnTextChanged.Callback.BEFORE_TEXT_CHANGED)
   void onBeforeTextChanged(CharSequence text) {

	  View view = LayoutInflater.from(this).inflate(R.layout.activity_main, null); // along with the layout providing the root

	  Button button2 = ButterKnife.findById(view, R.id.button02); // does the auto cast
	  button2.setText("Before changed" + text);
   }


   /*
	  Add @Optional annotation to the field or method, to supress the exception if target view cannot be found

	   @Optional @InjectView(R.id.might_not_be_there) TextView mightNotBeThere;

  	 @Optional @OnClick(R.id.maybe_missing) void onMaybeMissingClicked() {
		 // TODO ...
}
   * */

   @Override
   public boolean onCreateOptionsMenu(Menu menu){
	  // Inflate the menu; this adds items to the action bar if it is present.
	  getMenuInflater().inflate(R.menu.activity_main_menu, menu);
	  return true;
   }

   @Override
   public boolean onOptionsItemSelected(MenuItem item){
	  // Handle action bar item clicks here. The action bar will
	  // automatically handle clicks on the Home/Up button, so long
	  // as you specify a parent activity in AndroidManifest.xml.
	  int id = item.getItemId();

	  //noinspection SimplifiableIfStatement
	  if(id == R.id.action_settings){

		 toggleViews();

		 return true;
	  }

	  return super.onOptionsItemSelected(item);
   }

   // Custom views can bind to their own listeners by not specifying an ID.

   public class FancyButton extends Button {


	  public FancyButton(Context context){
		 super(context);
	  }

	  @OnClick
	  public void onClick(){
		 // TODO do something!
	  }
   }
}
