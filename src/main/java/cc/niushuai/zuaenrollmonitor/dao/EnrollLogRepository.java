package cc.niushuai.zuaenrollmonitor.dao;

import cc.niushuai.zuaenrollmonitor.entity.EnrollLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollLogRepository extends JpaRepository<EnrollLog, String> {
}
