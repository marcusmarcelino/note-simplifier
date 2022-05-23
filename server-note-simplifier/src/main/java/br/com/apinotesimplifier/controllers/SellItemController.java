package br.com.apinotesimplifier.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.apinotesimplifier.dto.SellItemDTO;
import br.com.apinotesimplifier.interfaces.SellItemService;
import br.com.apinotesimplifier.models.SellItem;

@RestController
@RequestMapping("/api/sellitems")
public class SellItemController {
  @Autowired
  private SellItemService sellItemService;

  @PostMapping("")
  public ResponseEntity<SellItem> save(@Valid @RequestBody SellItem sellItem) {
    return ResponseEntity.ok().body(sellItemService.save(sellItem));
  }

  @PostMapping("/all")
  public ResponseEntity<List<SellItem>> saveAllWithIds(@Valid @RequestBody List<SellItemDTO> sellItems) {
    return ResponseEntity.ok().body(sellItemService.saveAllWithIds(sellItems));
  }
}
