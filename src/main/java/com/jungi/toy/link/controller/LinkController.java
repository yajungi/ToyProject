package com.jungi.toy.link.controller;

import com.jungi.toy.config.auth.common.LoginUser;
import com.jungi.toy.config.auth.dto.SessionUser;
import com.jungi.toy.link.dto.LinkRequestDto;
import com.jungi.toy.link.dto.LinkResponseDto;
import com.jungi.toy.link.service.LinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.nio.channels.SeekableByteChannel;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class LinkController {
    private static final int PAGE_SIZE = 10;

    private final LinkService linkService;

    @GetMapping("/api/links/{id}")
    public LinkResponseDto findLinkById(@PathVariable int id) {
        return linkService.findLinkById(id);
    }

    @GetMapping("/api/links")
    public Map<String, Object> queryLinks(@RequestParam(value = "page") int page, @LoginUser SessionUser user) {
        Map<String, Object> linksJsonResponse = new HashMap<>();
        linksJsonResponse.put("links", linkService.findAllLinksByEmail(
                PageRequest.of(page, PAGE_SIZE, Sort.by("id").descending()),
                user.getEmail()));

        return linksJsonResponse;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/links")
    public void saveLink(@RequestBody LinkRequestDto linkRequestDto) {
        linkService.saveLink(linkRequestDto);
    }

    @PutMapping("/api/links/{id}")
    public int updateLink(@PathVariable int id, @RequestBody LinkRequestDto linkRequestDto) {
        return linkService.updateLink(id, linkRequestDto);
    }

    @DeleteMapping("/api/links/{id}")
    public void removeLink(@PathVariable int id) {
        linkService.removeLinkById(id);
    }
}
