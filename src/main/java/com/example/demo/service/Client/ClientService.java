package com.example.demo.service.Client;

import com.example.demo.model.response.CommonResult;
import com.example.demo.model.review.ReviewResponseDTO;
import org.springframework.data.domain.Slice;

public interface ClientService {

    public void setNickname(int clientIndex, String nickName);
    public CommonResult getClientFromNaver(String naverToken);
    public Slice<ReviewResponseDTO> getReviewByOwnerIndex(Integer itemId, Integer page);

}
