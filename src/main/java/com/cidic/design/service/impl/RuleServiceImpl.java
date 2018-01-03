package com.cidic.design.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cidic.design.dao.RuleDao;
import com.cidic.design.model.Rule;
import com.cidic.design.service.RuleService;

@Service
@Component
@Qualifier(value = "ruleServiceImpl")
@Transactional
public class RuleServiceImpl implements RuleService {

	@Autowired
	@Qualifier(value = "ruleDaoImpl")
	private RuleDao ruleDaoImpl;
	
	@Override
	public void createRule(Rule rule) {
		ruleDaoImpl.createRule(rule);
	}

	@Override
	public void updateRule(Rule rule) {
		ruleDaoImpl.updateRule(rule);
	}

	@Override
	public void deleteRule(int id) {
		ruleDaoImpl.deleteRule(id);
	}

	@Override
	public List<Rule> getAllRule() {
		
		return ruleDaoImpl.getAllRule();
	}

	@Override
	public Optional<Rule> getRuleById(int id) {
		// TODO Auto-generated method stub
		return ruleDaoImpl.getRuleById(id);
	}

}
