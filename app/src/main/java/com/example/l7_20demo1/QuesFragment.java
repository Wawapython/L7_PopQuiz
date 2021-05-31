package com.example.l7_20demo1;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.l7_20demo1.databinding.FragmentQuesBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public QuesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QuesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QuesFragment newInstance(String param1, String param2) {
        QuesFragment fragment = new QuesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LiveData liveData;
        //liveData = new ViewModelProvider(requireActivity(), SavedStateViewModelFactory(getActivity(),).get(LiveData.class);
        liveData = new ViewModelProvider(this, new SavedStateViewModelFactory(requireActivity().getApplication(), this)).get(LiveData.class);
        FragmentQuesBinding binding;
        liveData.generator();

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_ques, container, false);
        binding.setData(liveData);
        binding.setLifecycleOwner(requireActivity());
        final  StringBuilder builder = new StringBuilder();

        // 練習非介面上的綁定~
        liveData.getLeftNum().observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding.textView3.setText(String.valueOf(integer));
            }
        });

        View.OnClickListener OCL = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.button2:
                        builder.append("1");
                        break;
                    case R.id.button3:
                        builder.append("2");
                        break;
                    case R.id.button4:
                        builder.append("3");
                        break;
                    case R.id.button5:
                        builder.append("4");
                        break;
                    case R.id.button6:
                        builder.append("5");
                        break;
                    case R.id.button7:
                        builder.append("6");
                        break;
                    case R.id.button8:
                        builder.append("7");
                        break;
                    case R.id.button9:
                        builder.append("8");
                        break;
                    case R.id.button10:
                        builder.append("9");
                        break;
                    case R.id.button11:
                        builder.setLength(0);
                        break;
                    case R.id.button12:
                        builder.append("0");
                        break;
                }
                if (builder.length() == 0){
                    binding.textView7.setText(getString(R.string.input));
                }else{
                    binding.textView7.setText(builder.toString());
                }
            }
        };
        binding.button2.setOnClickListener(OCL);
        binding.button3.setOnClickListener(OCL);
        binding.button4.setOnClickListener(OCL);
        binding.button5.setOnClickListener(OCL);
        binding.button6.setOnClickListener(OCL);
        binding.button7.setOnClickListener(OCL);
        binding.button8.setOnClickListener(OCL);
        binding.button9.setOnClickListener(OCL);
        binding.button10.setOnClickListener(OCL);
        binding.button11.setOnClickListener(OCL);
        binding.button12.setOnClickListener(OCL);

        binding.button13.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (Integer.valueOf(builder.toString()).intValue()==liveData.getAns().getValue()){
                    liveData.answerCorr();
                    builder.setLength(0);
                    binding.textView7.setText(getString(R.string.correct));
                }else{
                    NavController controller = Navigation.findNavController(v);
                    if (liveData.winFlag){
                        controller.navigate(R.id.action_quesFragment_to_winFragment);
                        liveData.winFlag = false;
                        liveData.save();
                    }else{
                        controller.navigate(R.id.action_quesFragment_to_lossFragment);
                    }
                }
            }
        });
        return binding.getRoot();
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_ques, container, false);
    }
}