package com.example.alessandrofsouza.santanderapp.adapter;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.alessandrofsouza.santanderapp.R;
import com.example.alessandrofsouza.santanderapp.model.Cell;
import com.example.alessandrofsouza.santanderapp.utils.Utils;

import java.util.ArrayList;

public class ListaCellAdapter2 extends RecyclerView.Adapter<ListaCellAdapter2.ViewHolder> {

    private ArrayList<Cell> dataSet;

    private static final String TAG = "Santander ";

    CheckBox cb;
    EditText et;
    private ViewHolder vhet;

    public ListaCellAdapter2() {
        dataSet = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder( final ViewGroup parent, int viewType) {

        View viewEdit = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_edit_text, parent, false);
        View viewCheck = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_checkbox, parent, false);
        View viewText = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_text, parent, false);
        View viewButton = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_button_round, parent, false);
        //View viewSuccess = LayoutInflater.from(parent.getContext()).inflate(R.layout.success, parent, false);

        ViewHolder vhck = new ViewHolder(viewCheck);
        vhet = new ViewHolder(viewEdit);
        ViewHolder vhbt = new ViewHolder(viewButton);
        final Cell cell = dataSet.get(viewType);

        if (viewType == Utils.TYPE_FIELD) {
            return new ViewHolder(viewEdit);

        } else if (viewType == Utils.TYPE_SEND) {
            vhbt.roundedButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i(TAG, "countSends = " + getItemCount());
                }
            });

            return new ViewHolder(viewButton);


        } else if (viewType == Utils.TYPE_CHECKBOX) {
            vhck.checkBox.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    cb = (CheckBox) v;
                    Log.i(TAG, "isChange? " + cb.isChecked());
                }
            });

            return new ViewHolder(viewCheck);
        } else {
            return new ViewHolder(viewText);
        }


        /*
        if (viewType == Utils.TYPE_CHECKBOX) {
            return new ViewHolder(viewCheck);

        } else if (viewType == Utils.TYPE_FIELD) {
            return new ViewHolder(viewButton);

        } else if (viewType == Utils.TYPE_SEND) {
            return new ViewHolder(viewEdit);

        } else {
            return new ViewHolder(viewText);
        }
        */

    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final Cell cell = dataSet.get(position);
        Resources res = holder.itemView.getContext().getResources();

        /*
        if (cell.getType() == Utils.TYPE_FIELD) {
            holder.textInputLayout.setHint(cell.getMessage());

        } else if (cell.getType() == Utils.TYPE_SEND) {
            holder.roundedButton.setText(cell.getMessage());

        } else if (cell.getType() == Utils.TYPE_CHECKBOX) {
            holder.checkBox.setText(cell.getMessage());

        } else {
            holder.textView.setText(cell.getMessage());
        }
        */




        //Edit Texts
        if (cell.getType() == Utils.TYPE_FIELD) {
            holder.textInputLayout.setPadding(0, cell.getTopSpacing(), 0, 0);
            holder.textInputLayout.setHint(cell.getMessage());

            //Tipos de teclado
            if (cell.getTypefield().equals(String.valueOf(Utils.TYPEFIELD_TEXT_T))) {
                holder.textInputEditText.setInputType(InputType.TYPE_CLASS_TEXT);

            } else if (cell.getTypefield().equals(String.valueOf(Utils.TYPEFIELD_EMAIL_T))) {
                holder.textInputEditText.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                holder.textInputLayout.setVisibility(View.GONE);

            } else if (cell.getTypefield().equals(String.valueOf(Utils.TYPEFIELD_TELNUMBER_T))) {
                holder.textInputEditText.setInputType(InputType.TYPE_CLASS_PHONE);
            }

        //button
        } else if (cell.getType() == Utils.TYPE_SEND) {
            holder.roundedButton.setText(cell.getMessage());
            holder.roundedButton.setPadding(0, cell.getTopSpacing(), 0, cell.getTopSpacing());


            //checkbox
        } else if (cell.getType() == Utils.TYPE_CHECKBOX) {
            holder.checkBox.setOnCheckedChangeListener(null);
            holder.checkBox.setText(cell.getMessage());
            holder.checkBox.setTextSize(TypedValue.COMPLEX_UNIT_PX, res.getDimension(R.dimen.txtRegular));
            holder.checkBox.setTypeface(ResourcesCompat.getFont(holder.itemView.getContext(), R.font.dinpro_medium));
            holder.checkBox.setPadding(0, cell.getTopSpacing(), 0, cell.getTopSpacing());
            holder.checkBox.setChecked(cell.isHidden());

            holder.checkBox.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    cb = (CheckBox) v;
                    et = (EditText) holder.textInputEditText;

                    if(cb.isChecked()) {
                        //Log.i(TAG, "change? " + cb.isChecked());
                        //et.setVisibility(View.VISIBLE);

                        /*for (int i = 0; i < dataSet.size(); i++) {
                            Cell c =
                            //Log.i(TAG, "change? " + dataSet.get(position).getId());
                        }*/

                        /*for (Cell c : dataSet*//*investmentModel.screen.downInfo*//*) {
                            Log.i(TAG, dataSet.getClass().getName());
                        }*/
                    }
                }
            });

            //texto
        } else {
            //holder.textView.setText(cell.getMessage());
            holder.textView.setText("");
            holder.textView.setPadding(0, cell.getTopSpacing(), 0, cell.getTopSpacing());
            holder.textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, res.getDimension(R.dimen.txtRegular));
        }
    }

    @Override
    public int getItemViewType(int position) {
        Cell cell = dataSet.get(position);
        int viewType = cell.getType();

        return viewType;
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public void addListCell(ArrayList<Cell> listCell) {
        dataSet.addAll(listCell);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextInputLayout textInputLayout;
        private TextInputEditText textInputEditText;
        private TextView textView;
        private Button roundedButton;
        private CheckBox checkBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textInputLayout = itemView.findViewById(R.id.editTextLayout);
            textInputEditText = itemView.findViewById(R.id.editTextInput);
            textView = itemView.findViewById(R.id.nameTV);
            roundedButton = itemView.findViewById(R.id.buttonRound);
            checkBox = itemView.findViewById(R.id.checkBox);
        }
    }


    public class IntContainer {
        public int value;

        public IntContainer(int initialValue) {
            value = initialValue;
        }
    }
}