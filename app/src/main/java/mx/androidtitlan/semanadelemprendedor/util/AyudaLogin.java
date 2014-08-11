package mx.androidtitlan.semanadelemprendedor.util;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class AyudaLogin extends DialogFragment {

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		
		
		 AlertDialog.Builder builder = 
	        		new AlertDialog.Builder(getActivity());
	        
	        builder.setMessage("Esto es un mensaje de alerta.")

	        	   .setTitle("Información")
	               .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                       public void onClick(DialogInterface dialog, int id) {
                           dialog.cancel();
                       }
                   });
	        
	        return builder.create();
	}
	
	

}
