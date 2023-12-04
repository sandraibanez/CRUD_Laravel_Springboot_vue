package com.springboot.springboot.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.springboot.model.Mesa;
import com.springboot.springboot.model.MesaQueryParam;
import com.springboot.springboot.repository.MesaRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@CrossOrigin(origins = "http://localhost:5001")
@RestController
@RequestMapping("/api")
public class MesaController {
	
	
	@Autowired
	MesaRepository mesaRepository;
	@GetMapping("/mesa")
	public ResponseEntity<List<Mesa>> getAllMesas(@ModelAttribute MesaQueryParam mesaQueryParam) {
		try {
			mesaQueryParam.setName_mesa(mesaQueryParam.getName_mesa() + '%');
			Integer offset = (mesaQueryParam.getPage() - 1) * mesaQueryParam.getLimit();
			List<Mesa> mesas = new ArrayList<Mesa>();

			// Only category
			if ( mesaQueryParam.getCategories().length > 0 && mesaQueryParam.getCapacity() == null && mesaQueryParam.getOrder() ==null) {
				mesaRepository
				.findCategoriesOnMesa(mesaQueryParam.getCategories(),mesaQueryParam.getLimit(), offset)
				.forEach(mesas::add);
			} 
			// Categories with order
			else if (mesaQueryParam.getOrder() != 0 && mesaQueryParam.getCategories().length > 0) {
				if (mesaQueryParam.getOrder() == 1) {
					mesaRepository
							.findCategoriesOnMesaASC(mesaQueryParam.getCategories(),mesaQueryParam.getLimit(), offset)
							.forEach(mesas::add);
				} 
				else {
					mesaRepository
							.findCategoriesOnMesaDESC(mesaQueryParam.getCategories(),mesaQueryParam.getLimit(), offset)
							.forEach(mesas::add);
				}
			} // Only order
			else if (mesaQueryParam.getOrder() != 0 && mesaQueryParam.getCategories().length == 0 ) {
				if (mesaQueryParam.getOrder() == 1) {
					mesaRepository.findOrderedASC(mesaQueryParam.getName_mesa(),mesaQueryParam.getLimit(), offset)
							.forEach(mesas::add);
				} else {
					mesaRepository.findOrderedDESC(mesaQueryParam.getName_mesa(),mesaQueryParam.getLimit(), offset)
							.forEach(mesas::add);
				}
			}
			return new ResponseEntity<>(mesas, HttpStatus.OK);
		} catch (Exception e) {
			System.err.println(e);
			// return mesaQueryParam;
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/mesagetcapacity")
	public ResponseEntity<List<Mesa>> getcapacity(@ModelAttribute MesaQueryParam mesaQueryParam) {
		try {
			mesaQueryParam.setName_mesa(mesaQueryParam.getName_mesa() + '%');
			Integer offset = (mesaQueryParam.getPage() - 1) * mesaQueryParam.getLimit();
			List<Mesa> mesas = new ArrayList<Mesa>();
			if (mesaQueryParam.getCategories().length > 0 && mesaQueryParam.getCapacity() > 0) {
				mesaRepository
				.findByCapacityAndCategories(mesaQueryParam.getCapacity(), mesaQueryParam.getCategories(), mesaQueryParam.getLimit(), offset)
				.forEach(mesas::add);
			}
			
			
			return new ResponseEntity<>(mesas, HttpStatus.OK);
		} catch (Exception e) {
			System.err.println(e);
			// return mesaQueryParam;
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/mesagetcapacity2")
	public ResponseEntity<List<Mesa>> getcapacity2(@ModelAttribute MesaQueryParam mesaQueryParam) {
		try {
			mesaQueryParam.setName_mesa(mesaQueryParam.getName_mesa() + '%');
			Integer offset = (mesaQueryParam.getPage() - 1) * mesaQueryParam.getLimit();
			List<Mesa> mesas = new ArrayList<Mesa>();
			if( mesaQueryParam.getCategories() == null && mesaQueryParam.getCapacity() > 0 ){
						mesaRepository
						.findByCapacity(mesaQueryParam.getCapacity(),mesaQueryParam.getLimit(), offset)
						.forEach(mesas::add);
			}
			
			
			return new ResponseEntity<>(mesas, HttpStatus.OK);
		} catch (Exception e) {
			System.err.println(e);
			// return mesaQueryParam;
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/mesaall")
	public ResponseEntity<List<Mesa>> getall(@ModelAttribute MesaQueryParam mesaQueryParam) {
		try {
			mesaQueryParam.setName_mesa(mesaQueryParam.getName_mesa() + '%');
			Integer offset = (mesaQueryParam.getPage() - 1) * mesaQueryParam.getLimit();
			List<Mesa> mesas = new ArrayList<Mesa>();
			if( mesaQueryParam.getCategories() == null && mesaQueryParam.getCapacity() == null 
				&& mesaQueryParam.getOrder() == null){
				mesaRepository.findActive( mesaQueryParam.getLimit(), offset)
						.forEach(mesas::add);
			}
			
			
			return new ResponseEntity<>(mesas, HttpStatus.OK);
		} catch (Exception e) {
			System.err.println(e);
			// return mesaQueryParam;
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/mesaPaginate")
	public ResponseEntity<Integer> getAllMesasPaginate(@ModelAttribute MesaQueryParam mesaQueryParam) {
		try {
			mesaQueryParam.setName_mesa(mesaQueryParam.getName_mesa() + '%');
			Integer total = 0;
			// Only capacity
			if (mesaQueryParam.getCategories().length == 0 && mesaQueryParam.getCapacity() > 0) {
				total = mesaRepository.findByCapacityPaginate(mesaQueryParam.getCapacity());
			}
			// Only categories
			else if (mesaQueryParam.getCategories().length > 0 && mesaQueryParam.getCapacity() == 0) {
				total = mesaRepository.findCategoriesOnMesaPaginate(mesaQueryParam.getCategories(),
						mesaQueryParam.getName_mesa());
			}
			// Categories with capacity
			else if (mesaQueryParam.getCategories().length > 0 && mesaQueryParam.getCapacity() > 0) {
				total = mesaRepository.findByCapacityAndCategoriesPaginate(mesaQueryParam.getCapacity(),
						mesaQueryParam.getCategories());
			}
			else {
				total = mesaRepository.findActivePaginate(mesaQueryParam.getName_mesa());
			}

			return new ResponseEntity<>(total, HttpStatus.OK);
		} catch (Exception e) {
			System.err.println(e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/mesaInfinite")
	public ResponseEntity<List<Mesa>> getGigMesas(@ModelAttribute MesaQueryParam mesaQueryParam) {
		try {
			List<Mesa> mesas = new ArrayList<Mesa>();
			Integer limit = mesaQueryParam.getPage() * mesaQueryParam.getLimit();
			mesaRepository.findBigTables(limit).forEach(mesas::add);
			return new ResponseEntity<>(mesas, HttpStatus.OK);

		} catch (Exception e) {
			System.err.println(e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

	@GetMapping("/mesa/{id}")
	public ResponseEntity<Mesa> getOneLinkMesa(@PathVariable(required = true) Long id) {
		try {
			Optional<Mesa> mesa = mesaRepository.findById(id);
			if (mesa.isPresent()) {
				return new ResponseEntity<>(mesa.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}// get one

	
}