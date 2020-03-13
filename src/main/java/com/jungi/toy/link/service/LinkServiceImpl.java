package com.jungi.toy.link.service;

import com.jungi.toy.link.domain.Link;
import com.jungi.toy.link.dto.LinkRequestDto;
import com.jungi.toy.link.dto.LinkResponseDto;
import com.jungi.toy.link.repository.LinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class LinkServiceImpl implements LinkService {
    private final LinkRepository linkRepository;

    @Override
    public Page<Link> findAllLinks(Pageable pageable) {
        return linkRepository.findAll(pageable);
    }

    @Override
    public LinkResponseDto findLinkById(int id) {
        Link link = linkRepository.findById(id)
                                .orElseThrow(() -> new IllegalArgumentException("해당 링크가 없습니다. id= " + id));

        return new LinkResponseDto(link);
    }

    @Override
    @Transactional
    public void saveLink(LinkRequestDto linkRequestDto) {
        linkRepository.save(linkRequestDto.convertLink());
    }

    @Override
    @Transactional
    public int updateLink(int id, LinkRequestDto linkRequestDto) {
        Link link = linkRepository.findById(id)
                                .orElseThrow(() -> new IllegalArgumentException("해당 링크가 없습니다. id= " + id));

        link.updateLink(linkRequestDto.getUrl(), linkRequestDto.getContent());

        return id;
    }
}
